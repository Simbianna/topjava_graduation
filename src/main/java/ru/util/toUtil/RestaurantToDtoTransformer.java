package ru.util.toUtil;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.model.Restaurant;
import ru.dto.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RestaurantToDtoTransformer {
    private final DishToDtoTransformer dishTransformer;

    public RestaurantTo asToForUser(Restaurant restaurant) {
        return restaurant.getDishes() == null ?
                new RestaurantTo(restaurant.getId(), restaurant.getName()) :
                new RestaurantTo(restaurant.getId(), restaurant.getName(),
                        dishTransformer.asToList(restaurant.getDishes()));
    }

    public RestaurantTo asToForUserNoDishes(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public List<RestaurantTo> asToListForUser(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::asToForUser)
                .collect(Collectors.toList());

    }

    /*
    public static RestaurantTo getAsToWithActualMenu(Restaurant restaurant) {
        return restaurant.getDishes() == null ?
                new RestaurantTo(restaurant.getId(), restaurant.getName()) :
                new RestaurantTo(restaurant.getId(), restaurant.getName(), DishesUtil.asToFilteredByIncluded(restaurant.getDishes()));
    }

    public static List<RestaurantTo> getAsToListWithActualMenu(List<Restaurant> restaurants) {
        return Objects.requireNonNullElse(restaurants.stream()
                .map(RestaurantsUtil::getAsToWithActualMenu)
                .collect(Collectors.toList()), null);
    }
    */
}
