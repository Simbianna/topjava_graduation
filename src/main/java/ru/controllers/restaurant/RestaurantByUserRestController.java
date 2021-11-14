package ru.controllers.restaurant;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.service.RestaurantService;
import ru.dto.RestaurantTo;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(value = RestaurantByUserRestController.REST_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantByUserRestController {
    static final String REST_URL = "/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService restaurantService;

    @Cacheable("restaurants")
    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("get All restaurants");
        return restaurantService.getAllAsTo();
    }

    @GetMapping("/{id}")
    public RestaurantTo getById(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return restaurantService.getByIdAsTo(id);
    }

}
