package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    //Returns all restaurants even with empty lunch menu ordered by name ascending. Entities will be loaded without menu
    List<Restaurant> getAll();

    //Returns all restaurants with non-empty lunch menu ordered by name ascending. Entities will be loaded with menu
    List<Restaurant> getAllWithActualMenu();

    //Returns restaurant even if it`s lunch menu is empty. Null if not found. Entity will be loaded without menu
    Restaurant get(int id);

    //Null if not found or restaurant`s lunch menu is empty. Entity will be loaded with menu
    Restaurant getWithActualMenu(int id);

    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

}
