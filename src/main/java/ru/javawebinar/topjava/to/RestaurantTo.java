package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class RestaurantTo extends BaseTo{

    private final String name;
    private final List<Meal> todaysLunchMeals;
    private final Integer todaysRating;

    public RestaurantTo(Integer id, String name, List<Meal> todaysLunchMeals, Integer todaysRating) {
        super(id);
        this.name = name;
        this.todaysLunchMeals = todaysLunchMeals;
        this.todaysRating = todaysRating;
    }


    public String getName() {
        return name;
    }

    public List<Meal> getTodaysLunchMeals() {
        return todaysLunchMeals;
    }

    public Integer getTodaysRating() {
        return todaysRating;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", todaysLunchMeals=" + todaysLunchMeals +
                ", todaysRating=" + todaysRating +
                '}';
    }
}
