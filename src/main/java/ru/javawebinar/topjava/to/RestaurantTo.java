package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.Dish;

import java.util.List;

public class RestaurantTo extends BaseTo{

    private final String name;
    private final List<Dish> todaysLunchDishes;
    private final Integer todaysRating;

    public RestaurantTo(Integer id, String name, List<Dish> todaysLunchDishes, Integer todaysRating) {
        super(id);
        this.name = name;
        this.todaysLunchDishes = todaysLunchDishes;
        this.todaysRating = todaysRating;
    }


    public String getName() {
        return name;
    }

    public List<Dish> getTodaysLunchDishes() {
        return todaysLunchDishes;
    }

    public Integer getTodaysRating() {
        return todaysRating;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", todaysLunchDishes=" + todaysLunchDishes +
                ", todaysRating=" + todaysRating +
                '}';
    }
}
