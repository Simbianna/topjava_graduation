package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface DishRepository {

    // null if not found
    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if dish not found
    Dish get(int id);

    // ORDERED dateTime desc
    List<Dish> getAll(int restaurantId);

   /* // ORDERED dateTime desc
    List<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default Dish getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }*/
}
