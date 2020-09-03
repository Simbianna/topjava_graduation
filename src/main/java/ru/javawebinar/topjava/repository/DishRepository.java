package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Dish;

import java.util.List;

public interface DishRepository {

    // Returns all dishes (even those that are not included in the actual lunch menu) by restaurant Id ordered by dateTime desc
    List<Dish> getAllByRestaurantId(int restaurantId);

    // Returns dishes included in the actual lunch menu by restaurant Id ordered by dateTime desc
    List<Dish> getAllIncludedByRestaurantId(int restaurantId);

    // null if dish not found
    Dish get(int id, int restaurantId);

    // null if not found
    Dish save(Dish dish, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

}
