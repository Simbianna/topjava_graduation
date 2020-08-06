package ru.javawebinar.topjava.web.controllers.vote;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.RestaurantRepository;
import ru.javawebinar.topjava.repository.VoteRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getDaysBeginning;
import static ru.javawebinar.topjava.util.DateTimeUtil.getDaysEnd;
import static ru.javawebinar.topjava.util.ValidationUtil.*;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = VoteUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteUserRestController {
    static final String REST_URL = "/rest/profile/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;

    private final RestaurantRepository restaurantRepository;

    @Autowired
    VoteUserRestController(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Vote> getAll() {
        int userId = authUserId();
        log.info("get all votes for user {}", userId);
        return voteRepository.getAllForUser(userId);
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {} for user {}", id, userId);
        return voteRepository.getByIdForUser(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {}", id);
        checkNotFoundWithId(voteRepository.deleteForUser(id, userId), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> doVote(@Validated(View.Web.class) @RequestBody Vote vote) {
        log.info("create/update vote {}", vote);
        Assert.notNull(vote, "vote must not be null");
        int userId = authUserId();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Vote lastVote = voteRepository.getLastVoteForUserBetweenDateTimes(userId, getDaysBeginning(currentDateTime), getDaysEnd(currentDateTime));
        if (lastVote != null) {
            checkVoteCanBeUpdatedToday(currentDateTime);
            lastVote.setVotingDateTime(LocalDateTime.now());
            lastVote.setRestaurant(restaurantRepository.get(vote.getId()));
        }
        Assert.notNull(vote, "vote must not be null");
        checkNew(vote);


        checkVoteIsNewToday(lastVote);
        Vote created = voteRepository.saveForUser(vote,userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
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
