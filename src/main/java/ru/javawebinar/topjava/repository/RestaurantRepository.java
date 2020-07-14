package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Restaurant;
import java.util.List;

public interface RestaurantRepository {

    // null if not found
    Restaurant get(int id);

    // null if not found
    Restaurant getWithDishes(int id);

    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // ordered by name ascending
    List<Restaurant> getAll();

    // ordered by name ascending
    List<Restaurant> getAllWithDishes();

}
