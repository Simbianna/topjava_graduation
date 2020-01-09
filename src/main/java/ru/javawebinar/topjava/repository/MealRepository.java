package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.baseEntities.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    // null if updated meal do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to restaurantId
    Meal get(int id, int restaurantId);

    // ORDERED dateTime desc
    List<Meal> getAll(int restaurantId);

    // ORDERED dateTime desc
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int restaurantId);

    // ORDERED dateTime desc
    List<Meal> getForToday(LocalDate today, int restaurantId);

    default Meal getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}
