package ru.javawebinar.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.service.RestaurantService;
import ru.javawebinar.topjava.service.VoteService;
import ru.javawebinar.topjava.to.RestaurantTo;
import ru.javawebinar.topjava.util.RestaurantsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;

//TODO подумать, нужен ли UserID
@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping
    public List<Restaurant> getAllWithRatings() {
        return super.getAllWithRatings();
    }


/*

    public Restaurant get(int id) {

        return restaurantService.get(id);
    }

    public RestaurantTo getWithRating(int id, LocalDateTime date) {
        List<Vote> restaurantVotes = voteService.getAllForRestaurantForOneDay(id, date);
        Restaurant restaurant = restaurantService.get(id);
        return RestaurantsUtil.createWithRating(restaurant, restaurantVotes);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {}", id);
        restaurantService.delete(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkNew(restaurant);
        log.info("create restaurant {}", restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update restaurant {}", restaurant);
        restaurantService.update(restaurant);
    }
*/


}
