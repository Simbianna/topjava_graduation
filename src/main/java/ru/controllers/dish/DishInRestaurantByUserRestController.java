package ru.controllers.dish;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.service.DishService;
import ru.dto.DishTo;
import ru.util.toUtil.DishToDtoTransformer;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = DishInRestaurantByUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishInRestaurantByUserRestController {
    static final String REST_URL = "/restaurants/{restaurantId}/lunchMenu";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DishService dishService;
    private final DishToDtoTransformer dishTransformer;

    @GetMapping
    public List<DishTo> getAllIncludedInLunchMenu(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all dishes included in lunch menu for restaurant {}", restaurantId);
        return dishService.getAllIncludedByRestaurantIdDto(restaurantId);
    }

    @GetMapping("/{id}")
    public DishTo getById(@PathVariable("restaurantId") int restaurantId, @PathVariable int dishId) {
        log.info("get dish {} for restaurant {}", dishId, restaurantId);
        return dishService.getByIdForRestaurantAsDto(dishId, restaurantId);
    }
}
