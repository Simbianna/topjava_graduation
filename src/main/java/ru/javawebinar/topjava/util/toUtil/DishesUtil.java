package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.to.DishTo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DishesUtil {

    private DishesUtil() {
    }

    public static DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    static List<DishTo> asTo(List<Dish> dishes) {
        return Objects.requireNonNullElse(dishes.stream()
        .map(DishesUtil::asTo)
                .collect(Collectors.toList()), null);
       /* List<DishTo> dishesTo = new HashSet<>();
        for (Dish dish : dishes) {
            dishesTo.add(asTo(dish));
        }
        return dishesTo;*/
    }

    public static List<DishTo> asToFilteredByIncluded(List<Dish> dishes) {
        return Objects.requireNonNullElse(dishes.stream()
                .filter(Dish::isIncluded)
                .map(DishesUtil::asTo)
                .collect(Collectors.toList()), null);
       /* List<DishTo> dishesTo = new HashSet<>();
        for (Dish dish : dishes) {
            if (dish.isIncluded()) {
                dishesTo.add(asTo(dish));
            }
        }
        return dishesTo;*/
    }
}
