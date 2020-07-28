package ru.javawebinar.topjava.to;

import java.util.Set;

public class MenuTo extends BaseTo{

    private Set<DishTo> dishes;

    public MenuTo() {
    }

    public MenuTo(int id, Set<DishTo> dishes) {
        super(id);
        this.dishes = dishes;
    }

    public Set<DishTo> getDishes() {
        return dishes;
    }

    public void setDishes(Set<DishTo> dishes) {
        this.dishes = dishes;
    }


    @Override
    public String toString() {
        return "MenuTo{" +
                "dishes=" + dishes +
                ", id=" + id +
                '}';
    }
}
