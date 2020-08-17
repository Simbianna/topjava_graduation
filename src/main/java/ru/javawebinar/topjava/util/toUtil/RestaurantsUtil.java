package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantsUtil {
    private RestaurantsUtil() {
    }

    public static RestaurantTo asToForUser(Restaurant restaurant) {
        return restaurant.getDishes() == null ?
                new RestaurantTo(restaurant.getId(), restaurant.getName()) :
                new RestaurantTo(restaurant.getId(), restaurant.getName(), DishesUtil.asToList(restaurant.getDishes()));
    }

    public static RestaurantTo asToForUserNoDishes(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantTo> asToListForUser(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantsUtil::asToForUser)
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
