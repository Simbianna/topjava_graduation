package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.to.DishTo;

import java.util.HashSet;
import java.util.Set;

public class DishesUtil {

    private DishesUtil() {
    }

    private static DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    static Set<DishTo> asTo(Set<Dish> dishes) {
        Set<DishTo> dishesTo = new HashSet<>();
        for (Dish dish : dishes) {
            dishesTo.add(asTo(dish));
        }
        return dishesTo;
    }

    static Set<DishTo> asToFilteredByIncluded(Set<Dish> dishes) {
        Set<DishTo> dishesTo = new HashSet<>();
        for (Dish dish : dishes) {
            if (dish.isIncluded()) {
                dishesTo.add(asTo(dish));
            }
        }
        return dishesTo;
    }
}
