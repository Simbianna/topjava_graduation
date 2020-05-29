package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantRepository {

    // null if not found
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if restaurant not found
    Restaurant get(int id);

    // ORDERED dateTime desc
    List<Restaurant> getAll();

    default Restaurant getWithDishes(int id) {
        throw new UnsupportedOperationException();
    }

}
