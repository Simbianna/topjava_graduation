package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Restaurant;
import java.util.List;

public interface RestaurantRepository {

    // ordered by name ascending
    List<Restaurant> getAll();

    // null if not found
    Restaurant get(int id);

    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant getWithMenu(int id);

    // ordered by name ascending
    List<Restaurant> getAllWithMenus();

}
