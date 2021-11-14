package ru.controllers.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.View;
import ru.model.Restaurant;
import ru.service.RestaurantService;

import java.net.URI;
import java.util.List;

import static ru.util.ValidationUtil.*;


@RestController
@RequestMapping(value = RestaurantByAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantByAdminRestController {
    static final String REST_URL = "/admin/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantByAdminRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Cacheable("restaurants")
    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return restaurantService.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return restaurantService.getById(id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        restaurantService.deleteById(id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Restaurant created = restaurantService.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Restaurant restaurant, @PathVariable int id) {
        log.info("update restaurant {} with id = {}", restaurant, id);
        restaurantService.update(restaurant, id);
    }
}
