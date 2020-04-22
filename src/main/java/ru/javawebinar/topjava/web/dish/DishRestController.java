package ru.javawebinar.topjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.service.DishService;
import ru.javawebinar.topjava.web.util.SecurityUtil;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.web.util.SecurityUtil.authUserId;

@RestController
public class DishRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish get(int id) {
        log.info("get dish {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        int userId = authUserId();
        log.info("delete dish {}", id);
        service.delete(id, userId);
    }

    public void getAll(int restaurantId) {
        log.info("get all dishes for restaurant {}", restaurantId);
        service.get(restaurantId);
    }

    public void update(Dish dish, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(dish, id);
        log.info("update dish {}", dish);
        service.update(dish , userId);
    }


}
