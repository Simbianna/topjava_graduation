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

    // null if not found. Restaurant without menu will not be shown
    Restaurant getWithActualMenu(int id);

    //Restaurants without menus will not be shown
    List<Restaurant> getAllWithActualMenu();

    // null if not found
    Restaurant getWithDishes(int id);

    List<Restaurant> getAllWithDishes();

}
