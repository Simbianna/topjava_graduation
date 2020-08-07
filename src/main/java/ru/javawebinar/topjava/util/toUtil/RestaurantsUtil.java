package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestaurantsUtil {
    private RestaurantsUtil() {
    }

    public static RestaurantTo getAsToForUser(Restaurant restaurant) {
        return restaurant.getDishes() == null ?
                new RestaurantTo(restaurant.getId(), restaurant.getName()) :
                new RestaurantTo(restaurant.getId(), restaurant.getName(), DishesUtil.asTo(restaurant.getDishes()));
    }

    public static List<RestaurantTo> getAsToListForUser(List<Restaurant> restaurants) {
        return Objects.requireNonNullElse(restaurants.stream()
                .map(RestaurantsUtil::getAsToForUser)
                .collect(Collectors.toList()), null);

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
