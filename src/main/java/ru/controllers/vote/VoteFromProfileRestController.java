package ru.controllers.vote;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.View;
import ru.dto.VoteTo;
import ru.model.Vote;
import ru.service.VoteService;

import java.net.URI;
import java.util.List;

import static ru.util.SecurityUtil.authUserId;

@RestController
@AllArgsConstructor
@RequestMapping(value = VoteFromProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteFromProfileRestController {
    static final String REST_URL = "/profile/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService voteService;

    @GetMapping
    public List<VoteTo> getAll() {
        int userId = authUserId();
        log.info("get all votes for user {}", userId);
        return voteService.getAllForUserWithRestaurant(userId);
    }

    @GetMapping("/{id}")
    public VoteTo get(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {} for user {}", id, userId);
        return voteService.getByIdForUserWithRestaurant(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = authUserId();
        log.info("get vote {} for user {}", id, userId);
        voteService.deleteByIdForUser(id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@Validated(View.Web.class) @RequestBody Vote vote) {
        int userId = authUserId();
        log.info("create vote {} for user {}", vote, userId);
        Vote created = voteService.createForUser(vote, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int voteId) {
        int userId = authUserId();
        log.info("update vote {} for user {}", vote, userId);
        voteService.updateForUser(vote, voteId, userId);
    }

}
