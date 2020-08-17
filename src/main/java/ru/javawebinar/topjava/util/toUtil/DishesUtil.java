package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.to.DishTo;

import java.util.List;
import java.util.stream.Collectors;

public class DishesUtil {

    private DishesUtil() {
    }

    public static DishTo asToList(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    static List<DishTo> asToList(List<Dish> dishes) {
        return dishes.stream()
                .map(DishesUtil::asToList)
                .collect(Collectors.toList());
    }

    public static List<DishTo> asToListFilteredByIncluded(List<Dish> dishes) {
        return dishes.stream()
                .filter(Dish::isIncluded)
                .map(DishesUtil::asToList)
                .collect(Collectors.toList());
    }
}
