package ru.javawebinar.topjava.web.controllers.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.RestaurantRepository;
import ru.javawebinar.topjava.repository.VoteRepository;

import java.net.URI;
import java.time.LocalDateTime;

import static ru.javawebinar.topjava.util.ValidationUtil.*;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = VoteRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestaurantRestController {
    static final String REST_URL = "/rest/restaurants/{restaurantId}/vote";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    VoteRestaurantRestController(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {}", id);
        checkNotFoundWithId(voteRepository.deleteForUser(id, userId), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@PathVariable("restaurantId") int restaurantId) {
        log.info("create vote for restaurant {}", restaurantId);
        int userId = authUserId();
        Vote lastVote = voteRepository.getLastVoteForUserBetweenDateTimes(userId);
        checkVoteIsNewToday(lastVote);
        Vote newVote = new Vote(LocalDateTime.now(), restaurantRepository.get(restaurantId));
        Vote created = voteRepository.saveForUser(newVote, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id,
                       @RequestParam int restaurant_id) {
        log.info("update vote {}", id);
        checkVoteCanBeUpdatedToday();
        int userId = authUserId();
        Vote vote = voteRepository.getById(id);
        checkVoteCanBeUpdated(vote.getVotingDateTime());
        Vote lastVote = voteRepository.getLastVoteForUserBetweenDateTimes(userId);
        Assert.notNull(vote, "vote must not be null");

        checkNotFoundWithId(voteRepository.saveForAdmin(vote), vote.getId());
    }

}
