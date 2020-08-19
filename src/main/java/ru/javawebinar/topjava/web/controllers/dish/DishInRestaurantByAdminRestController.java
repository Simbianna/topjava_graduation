package ru.javawebinar.topjava.web.controllers.dish;

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
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.repository.DishRepository;

import java.net.URI;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

@RestController
@RequestMapping(value = DishInRestaurantByAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishInRestaurantByAdminRestController {
    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository dishRepository;

    @Autowired
    public DishInRestaurantByAdminRestController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all dishes for restaurant {}", restaurantId);
        return dishRepository.getAllByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("restaurantId") int restaurantId, @PathVariable int id) {
        log.info("get dish {} for restaurant {}", id, restaurantId);
        return checkNotFoundWithId(dishRepository.get(id, restaurantId), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("restaurantId") int restaurantId, @PathVariable int id) {
        log.info("delete dish {} for restaurant {}", id, restaurantId);
        checkNotFoundWithId(dishRepository.delete(id, restaurantId), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@PathVariable("restaurantId") int restaurantId, @Validated(View.Web.class) @RequestBody Dish dish) {
        log.info("create dish {} for restaurant {}", dish, restaurantId);
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        Dish created = dishRepository.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("restaurantId") int restaurantId, @Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish {} for restaurant", dish);
        Assert.notNull(dish, "Dish must not be null");
        assureIdConsistent(dish, id);
        dish.setAdded(dishRepository.get(id, restaurantId).getAdded());
        checkNotFoundWithId(dishRepository.save(dish, restaurantId), dish.getId());
    }

    @Transactional
    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void includeInActualMenu(@PathVariable("restaurantId") int restaurantId, @PathVariable int id, @RequestParam boolean included) {
        log.info(included ? "include {}" : "exclude {}", id);
        Dish dish = checkNotFoundWithId(dishRepository.get(id, restaurantId), id);
        dish.setIncluded(included);
        checkNotFoundWithId(dishRepository.save(dish, restaurantId), dish.getId());
    }

    @GetMapping("/lunchMenu")
    public List<Dish> getAllIncludedInLunchMenu(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all dishes included in lunch menu for restaurant {}", restaurantId);
        return dishRepository.getAllIncludedByRestaurantId(restaurantId);
    }
}
