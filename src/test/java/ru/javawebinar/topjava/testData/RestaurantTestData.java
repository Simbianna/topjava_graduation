package ru.javawebinar.topjava.testData;

import ru.javawebinar.topjava.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int STEAK_HOUSE_ID = START_SEQ + 2;
    public static final int ITALIAN_ID = START_SEQ + 3;
    public static final int VIETNAM_ID = START_SEQ + 4;

    public static final Restaurant STEAK_HOUSE = new Restaurant(STEAK_HOUSE_ID, "SteakHouse");
    public static final Restaurant ITALIAN = new Restaurant(ITALIAN_ID, "Italian restaurant");
    public static final Restaurant VIETNAM = new Restaurant(VIETNAM_ID, "Vietnam restaurant");

    public static final List<Restaurant> ALL_RESTAURANTS = List.of(STEAK_HOUSE, ITALIAN, VIETNAM);

    public static final Restaurant RUSSIAN = new Restaurant("Russian Restaurant");

    public static Restaurant getRussianCreated() {
        return RUSSIAN;
    }

    public static Restaurant getItalianUpdated() {
        return new Restaurant(ITALIAN_ID, "New");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "lunchMenu", "todaysRating");
    }

    public static void assertMatch(List<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(List<Restaurant> actual, List<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("todaysRating", "lunchMenu", "rating").isEqualTo(expected);
    }


}
