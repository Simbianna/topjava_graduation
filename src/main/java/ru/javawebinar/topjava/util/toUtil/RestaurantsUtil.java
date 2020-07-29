package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestaurantsUtil {
    private RestaurantsUtil() {
    }

    public static List<RestaurantTo> getAsToList(List<Restaurant> restaurants) {
        return Objects.requireNonNullElse(restaurants.stream()
                .map(RestaurantsUtil::getAsTo)
                .collect(Collectors.toList()), null);

    }

    public static RestaurantTo getAsTo(Restaurant restaurant) {
        return restaurant.getDishes() == null?
        new RestaurantTo(restaurant.getId(), restaurant.getName()):
        new RestaurantTo(restaurant.getId(), restaurant.getName(), DishesUtil.asTo(restaurant.getDishes()));
    }

}
