package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RestaurantsUtil {

    public static final int DEFAULT_RATING = 0;

    public RestaurantsUtil() {
    }

    public static RestaurantTo createWithRating(Restaurant restaurant, List<Vote> votes) {
        int rating = votes != null ? votes.size() : DEFAULT_RATING;
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getDishes(), rating);
    }

   /* public static List<RestaurantTo> getWithRatings(Collection<Restaurant> restaurants, Collection<Vote> votes) {
        for (Restaurant r : restaurants){

        }
    }*/

   /* public static List<RestaurantTo> getWithRatings(Collection<Restaurant> restaurants) {

    }*/

}
