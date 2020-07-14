package ru.javawebinar.topjava.to;

import ru.javawebinar.topjava.model.Dish;

import java.util.List;
import java.util.Set;

public class RestaurantTo extends BaseTo{

    private final String name;
    private final Set<Dish> lunchMenu;
    private final Integer rating;

    public RestaurantTo(Integer id, String name, Set<Dish> lunchMenu, Integer rating) {
        super(id);
        this.name = name;
        this.lunchMenu = lunchMenu;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public Set<Dish> getLunchMenu() {
        return lunchMenu;
    }

    public Integer getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lunchMenu=" + lunchMenu +
                ", rating=" + rating +
                '}';
    }
}
