package ru.controllers.dish;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.View;
import ru.model.Dish;
import ru.service.DishService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = DishInRestaurantByAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishInRestaurantByAdminRestController {
    static final String REST_URL = "/admin/restaurants/{restaurantId}/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DishService dishService;

    @GetMapping
    public List<Dish> getAllForRestaurant(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all dishes for restaurant {}", restaurantId);
        return dishService.getAllByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    public Dish getByIdForRestaurant(@PathVariable("restaurantId") int restaurantId, @PathVariable int id) {
        log.info("get dish {} for restaurant {}", id, restaurantId);
        return dishService.getByIdForRestaurant(id, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteByIdForRestaurant(@PathVariable("restaurantId") int restaurantId, @PathVariable int id) {
        log.info("delete dish {} for restaurant {}", id, restaurantId);
        dishService.deleteByIdForRestaurant(id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createForRestaurant(@PathVariable("restaurantId") int restaurantId, @Validated(View.Web.class) @RequestBody Dish dish) {
        log.info("create dish {} for restaurant {}", dish, restaurantId);
        Dish created = dishService.createForRestaurant(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateForRestaurant(@PathVariable("restaurantId") int restaurantId, @Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish {} for restaurant", dish);
        dishService.updateForRestaurant(dish, restaurantId, id);
    }

    @Transactional
    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void includeInOrExcludeFromActualMenu(@PathVariable("restaurantId") int restaurantId, @PathVariable int id, @RequestParam boolean isIncluded) {
        log.info(isIncluded ? "include {}" : "exclude {}", id);
        dishService.includeInOrExcludeFromActualMenu(restaurantId, id, isIncluded);
    }


    @GetMapping("/lunchMenu")
    public List<Dish> getAllIncludedInLunchMenu(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all dishes included in lunch menu for restaurant {}", restaurantId);
        return dishService.getAllIncludedByRestaurantId(restaurantId);
    }
}
