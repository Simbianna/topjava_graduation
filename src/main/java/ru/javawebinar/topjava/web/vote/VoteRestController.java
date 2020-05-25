package ru.javawebinar.topjava.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.service.VoteService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;
import static ru.javawebinar.topjava.web.util.SecurityUtil.authUserId;

@RestController
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote get(int id) {
        int userId = authUserId();
        log.info("get vote {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = authUserId();
        log.info("delete vote {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Vote> getAll() {
        int userId = authUserId();
        log.info("get all votes for user {}", userId);
        return service.getAllForUser(userId);
    }

    public Vote create(Vote vote) {
        int userId = authUserId();
        Vote lastVote = service.getLastForUser(userId);
        checkVoteIsNewToday(lastVote);
        checkNew(vote);
        log.info("create vote {} for user {}", vote, userId);
        return service.create(vote, userId);
    }

    public void update(Vote vote) {
        int userId = authUserId();
        LocalDateTime votingTime = LocalDateTime.now();
        vote.setVotingDateTime(votingTime);
        checkVoteCanBeUpdated(votingTime);
        assureIdConsistent(vote, userId);
        service.update(vote, userId);
    }

}
