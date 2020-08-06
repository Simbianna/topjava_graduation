package ru.javawebinar.topjava.to;

import java.util.List;

public class RestaurantTo extends BaseTo {

    private String name;
    private List<DishTo> lunchMenu;

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public RestaurantTo(Integer id, String name, List<DishTo> lunchMenu) {
        super(id);
        this.name = name;
        this.lunchMenu = lunchMenu;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lunchMenu=" + lunchMenu +
                '}';
    }
}
