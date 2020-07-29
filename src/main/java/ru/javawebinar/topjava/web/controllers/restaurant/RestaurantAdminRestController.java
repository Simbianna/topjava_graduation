package ru.javawebinar.topjava.web.controllers.restaurant;

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
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.repository.RestaurantRepository;

import java.net.URI;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;


@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController {
    static final String REST_URL = "/rest/admin/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantAdminRestController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return restaurantRepository.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createResponseEntity(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
        log.info("create {}", restaurant);
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update {} with id = {}", restaurant, id);
        Assert.notNull(restaurant, "restaurant must not be null");
        assureIdConsistent(restaurant, id);
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }
}
