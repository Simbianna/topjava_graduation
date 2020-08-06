package ru.javawebinar.topjava.web.controllers.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.repository.RestaurantRepository;
import ru.javawebinar.topjava.to.RestaurantTo;
import ru.javawebinar.topjava.util.toUtil.RestaurantsUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = RestaurantByUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantByUserRestController {
    static final String REST_URL = "/rest/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantByUserRestController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("get All restaurants with lunchMenu");
        return RestaurantsUtil.getAsToListForUser(restaurantRepository.getAllWithActualMenu());
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get restaurant {} with lunchMenu", id);
        return RestaurantsUtil.getAsToForUser(checkNotFoundWithId(restaurantRepository.getWithActualMenu(id), id));
    }

}
