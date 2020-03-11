package ru.javawebinar.topjava.testData;

import ru.javawebinar.topjava.model.Restaurant;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.testData.MealTestData.*;

public class RestaurantTestData {
    public static final int STEAK_HOUSE_ID = START_SEQ + 2;
    public static final int ITALIAN_ID = START_SEQ + 3;
    public static final int VIETNAM_ID = START_SEQ + 4;

    public static final Restaurant STEAK_HOUSE_D1 = new Restaurant(STEAK_HOUSE_ID, "SteakHouse");
    public static final Restaurant STEAK_HOUSE_D2 = new Restaurant(STEAK_HOUSE_ID, "SteakHouse");
    public static final Restaurant ITALIAN_D1 = new Restaurant(ITALIAN_ID, "Italian restaurant");
    public static final Restaurant ITALIAN_D2 = new Restaurant(ITALIAN_ID, "Italian restaurant");
    public static final Restaurant VIETNAM_D1 = new Restaurant(VIETNAM_ID, "Vietnam restaurant");
    public static final Restaurant VIETNAM_D2 = new Restaurant(VIETNAM_ID, "Vietnam restaurant");




}
