package ru.service;

import ru.dto.DishTo;
import ru.model.Dish;

import java.util.List;

public interface DishService {
    Dish getByIdForRestaurant(int dishId, int restaurantId);

    DishTo getByIdForRestaurantAsDto(int dishId, int restaurantId);

    Dish createForRestaurant(Dish dish, int restaurantId);

    List<Dish> getAllByRestaurantId(int restaurantId);

    void deleteByIdForRestaurant(int dishId, int restaurantId);

    List<Dish> getAllIncludedByRestaurantId(int restaurantId);

    Dish updateForRestaurant(Dish dish, int restaurantId, int dishId);

    void includeInOrExcludeFromActualMenu(int restaurantId, int dishId, boolean isIncluded);

    List<DishTo> getAllIncludedByRestaurantIdDto(int restaurantId);
}
