package ru.javawebinar.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.service.RestaurantService;
import ru.javawebinar.topjava.to.RestaurantTo;
import ru.javawebinar.topjava.util.RestaurantsUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService restaurantService;

    public List<Restaurant> getAllWithRatings(){
        log.info("getAll with ratings");
        return restaurantService.getAllWithRatings();
    }

    public List<Restaurant> getAllBase() {
        log.info("getAll");
        return restaurantService.getAll();
    }
   /* public List<RestaurantTo> getAll(){
        log.info("getAll with ratings");
        RestaurantsUtil.createWithRating(restaurantService.getAll())
        return restaurantService.getAll();
    }*/


    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantService.get(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        restaurantService.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id = {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }



}
