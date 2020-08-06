package ru.javawebinar.topjava.web.controllers.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;


import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = VoteByAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteByAdminRestController {
    static final String REST_URL = "/rest/admin/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;

    @Autowired
    VoteByAdminRestController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping
    public List<Vote> getAll() {
        log.info("get all votes");
        return voteRepository.getAll();
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get all votes");
        return checkNotFoundWithId(voteRepository.getById(id), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("get vote {}", id);
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int id){
        log.info("update vote {}", id);
        Assert.notNull(vote, "vote must not be null");
        assureIdConsistent(vote, id);
        checkNotFoundWithId(voteRepository.saveForAdmin(vote), vote.getId());
    }

}
