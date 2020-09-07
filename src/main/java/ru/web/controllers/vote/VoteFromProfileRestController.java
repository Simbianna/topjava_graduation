package ru.web.controllers.vote;

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
import ru.View;
import ru.model.Vote;
import ru.repository.VoteRepository;
import ru.to.VoteTo;
import ru.util.exception.IllegalRequestDataException;
import ru.util.toUtil.VotesUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.util.ValidationUtil.*;
import static ru.util.ValidationUtil.checkNotFoundWithId;
import static ru.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = VoteFromProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteFromProfileRestController {
        static final String REST_URL = "/rest/profile/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;

    @Autowired
    VoteFromProfileRestController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
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
        log.info("get vote {} for user {}", id, userId);
        checkNotFoundWithId(voteRepository.deleteByIdForUser(id, userId), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@Validated(View.Web.class) @RequestBody Vote vote) {
        int userId = authUserId();
        log.info("create vote {} for user {}", vote, userId);
        Assert.notNull(vote, "vote must not be null");
        LocalDate currentDate = LocalDate.now();
        if (voteRepository.getForUserByDate(userId, currentDate) != null) {
            throw new IllegalRequestDataException("can`t create new vote today");
        }
        checkNew(vote);
        vote.setVotingDate(currentDate);
        Vote created = voteRepository.saveForUser(vote, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int id) {
        int userId = authUserId();
        log.info("update vote {} for user {}", vote, userId);
        Assert.notNull(vote, "vote must not be null");
        LocalDateTime currentDateTime = LocalDateTime.now();
        checkVoteCanBeUpdatedToday(currentDateTime);
        assureIdConsistent(vote, id);
        vote.setVotingDate(LocalDate.now());
        checkNotFoundWithId(voteRepository.saveForUser(vote,userId), vote.getId());
    }

}
