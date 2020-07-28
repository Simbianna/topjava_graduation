package ru.javawebinar.topjava.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantUserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserRestController extends AbstractRestaurantController{
    static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping
    public List<RestaurantTo> getAllAsTo() {
        return super.getAllAsTo();
    }

    @GetMapping("/{id}")
    public RestaurantTo getAsTo(@PathVariable int id) {
        return super.getAsTo(id);
    }

}
