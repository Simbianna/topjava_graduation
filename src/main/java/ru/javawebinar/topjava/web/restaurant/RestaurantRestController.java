package ru.javawebinar.topjava.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.service.RestaurantService;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.util.SecurityUtil;

import java.util.List;

@Controller
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {}", id);
        service.delete(id, userId);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants {}");
        return service.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkNew(restaurant);
        log.info("create restaurant {}", restaurant);
        return service.create(restaurant, userId);
    }

    public void update(Restaurant restaurant, int id){
        int userId = SecurityUtil.authUserId();
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update restaurant {}", restaurant);
        service.update(restaurant, userId);

    }
}
