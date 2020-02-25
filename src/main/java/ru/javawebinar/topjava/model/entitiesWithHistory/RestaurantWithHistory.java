package ru.javawebinar.topjava.model.entitiesWithHistory;

import ru.javawebinar.topjava.model.baseEntities.Meal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;



// Class Not Used
public class RestaurantWithHistory {

    private AtomicInteger todaysRating;
    private List<Meal> todaysLunchMeals;
    private Map<LocalDate, List <Meal>> lunchHistory;
    private Map<LocalDate, AtomicInteger> ratingHistory;
}
