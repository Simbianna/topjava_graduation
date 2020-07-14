package ru.javawebinar.topjava.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.Restaurant;

import java.util.List;

//TODO подумать, нужен ли UserID
@RestController
@RequestMapping(value = RestaurantAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/admin/restaurants";

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
    /*

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
