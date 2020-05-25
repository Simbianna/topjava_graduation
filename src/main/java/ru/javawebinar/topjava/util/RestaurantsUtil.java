package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.List;

public class RestaurantsUtil {

    public static final int DEFAULT_RATING = 0;

    public RestaurantsUtil() {
    }

    public static RestaurantTo getWithRating(Restaurant restaurant, List<Vote> votes) {
        int rating = votes != null ? votes.size() : DEFAULT_RATING;
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getDishes(), rating);
    }

   /* public static List<RestaurantTo> getWithRatings(Collection<Restaurant> restaurants) {

    }*/

}
