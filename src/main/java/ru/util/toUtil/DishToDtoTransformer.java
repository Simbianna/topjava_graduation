package ru.util.toUtil;

import org.springframework.stereotype.Component;
import ru.model.Dish;
import ru.dto.DishTo;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class DishToDtoTransformer {

    private DishToDtoTransformer() {
    }

    public DishTo asTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public List<DishTo> asToList(List<Dish> dishes) {
        return dishes.stream()
                .map(this::asTo)
                .collect(Collectors.toList());
    }

    public List<DishTo> asToList(List<Dish> dishes, Predicate<Dish> predicate) {
        return dishes.stream()
                .filter(predicate)
                .map(this::asTo)
                .collect(Collectors.toList());
    }
}
