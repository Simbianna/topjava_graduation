package ru.javawebinar.topjava.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.web.restaurant.AbstractRestaurantController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.List;

@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController extends AbstractDishRestController {
      static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/dishes";

    @Override
    @GetMapping
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
