package ru.controllers.vote;

import lombok.AllArgsConstructor;
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
import ru.dto.VoteTo;
import ru.service.VoteService;
import ru.util.toUtil.VoteToDtoTransformer;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.util.SecurityUtil.authUserId;

@AllArgsConstructor
@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    static final String REST_URL = "/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final VoteService voteService;
    private final RestaurantRepository restaurantRepository;


    @GetMapping
    public List<Vote> getAll() {
        log.info("get all votes");
        return voteService.getAllWithRestaurant();
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote {}", id);
        return voteService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Vote> doVote(@RequestParam int restaurantId) {
        int userId = authUserId();
        log.info("create/update vote for user {}", userId);

        Vote vote = voteService.voteForRestaurant(restaurantId, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(vote);
    }
}
