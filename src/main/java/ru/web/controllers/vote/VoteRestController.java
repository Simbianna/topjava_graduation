package ru.web.controllers.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.model.Restaurant;
import ru.model.Vote;
import ru.repository.RestaurantRepository;
import ru.repository.VoteRepository;
import ru.to.VoteTo;
import ru.util.toUtil.VotesUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.util.ValidationUtil.*;
import static ru.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/rest/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    VoteRestController(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Vote> getAll() {
        log.info("get all votes");
        return voteRepository.getAllWithRestaurant();
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote {}", id);
        return checkNotFoundWithId(voteRepository.getByIdWithRestaurant(id), id);
    }

    @PostMapping
    public ResponseEntity<VoteTo> doVote(@RequestParam int restaurantId) {
        int userId = authUserId();
        log.info("create/update vote for user {}", userId);
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(restaurantId), restaurantId);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Vote todaysVote = voteRepository.getForUserByDateWithRestaurant(userId, currentDateTime.toLocalDate());
        Vote persistentVote;
        if (todaysVote != null) {
            checkVoteCanBeUpdatedToday(currentDateTime);
            todaysVote.setVotingDate(currentDateTime.toLocalDate());
            todaysVote.setRestaurant(restaurant);
            persistentVote = voteRepository.saveForUser(todaysVote, userId);
        } else {
            persistentVote = voteRepository.saveForUser(new Vote(LocalDate.now(), restaurant), userId);
        }
        VoteTo persistentVoteTo = VotesUtil.asToFotUser(persistentVote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(persistentVoteTo.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(persistentVoteTo);
    }
}
