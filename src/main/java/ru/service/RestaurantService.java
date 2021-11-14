package ru.service;

import ru.dto.RestaurantTo;
import ru.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAll();

    void deleteById(int id);

    Restaurant getById(int id);

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant, int id);

    List<RestaurantTo> getAllAsTo();

    RestaurantTo getByIdAsTo(int id);
}
