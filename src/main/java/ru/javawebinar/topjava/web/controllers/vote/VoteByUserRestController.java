package ru.javawebinar.topjava.web.controllers.vote;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.RestaurantRepository;
import ru.javawebinar.topjava.repository.VoteRepository;
import ru.javawebinar.topjava.to.VoteTo;
import ru.javawebinar.topjava.util.toUtil.VotesUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = VoteByUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteByUserRestController {
    static final String REST_URL = "/rest/profile/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteByUserRestController(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<VoteTo> getAll() {
        int userId = authUserId();
        log.info("get all votes for user {}", userId);
        return VotesUtil.asToListForUser(voteRepository.getAllForUserWithRestaurant(userId));
    }

    @GetMapping("/{id}")
    public VoteTo get(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {} for user {}", id, userId);
        return VotesUtil.asToFotUser(checkNotFoundWithId(voteRepository.getByIdForUserWithRestaurant(id, userId), id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {}", id);
        checkNotFoundWithId(voteRepository.deleteByIdForUser(id, userId), id);
    }

    @PostMapping
    public ResponseEntity<VoteTo> doVote(@RequestParam int restaurantId) {
        log.info("create/update vote");
        int userId = authUserId();
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
            persistentVote = voteRepository.saveForUser(new Vote(LocalDate.now(),restaurant ), userId);
        }
        VoteTo persistentVoteTo = VotesUtil.asToFotUser(persistentVote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(persistentVoteTo.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(persistentVoteTo);
    }

/*     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<VoteTo> doVote(@Validated(View.Web.class) @RequestBody Vote vote) {
        log.info("create/update vote {}", vote);
        Assert.notNull(vote, "vote must not be null");
        int userId = authUserId();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Vote todaysVote = voteRepository.getForUserByDateWithRestaurant(userId, currentDateTime.toLocalDate());
        Vote persistentVote;
        if (todaysVote != null) {
            checkVoteCanBeUpdatedToday(currentDateTime);
            todaysVote.setVotingDate(currentDateTime.toLocalDate());
            todaysVote.setRestaurant(vote.getRestaurant());
            persistentVote = voteRepository.saveForUser(todaysVote, userId);
        } else {
            vote.setVotingDate(LocalDate.now());
            persistentVote = voteRepository.saveForUser(vote, userId);
        }
        VoteTo persistentVoteTo = VotesUtil.asToFotUser(persistentVote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(persistentVoteTo.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(persistentVoteTo);*/
}
