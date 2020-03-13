package ru.javawebinar.topjava.testData;


import ru.javawebinar.topjava.model.Dish;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.*;

public class DishTestData {
    public static final int MEAL1_ID = START_SEQ + 5;

    public static final Dish ITALIAN_DISH_1 = new Dish(MEAL1_ID, "Daily pizza", 500.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Dish ITALIAN_DISH_2 = new Dish(MEAL1_ID, "Roasted salmon", 700.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Dish VIETNAM_DISH_1 = new Dish(MEAL1_ID + 1, "Grilled shrimps", 600.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Dish STEAK_DISH_1 = new Dish(MEAL1_ID + 2, "Ribeye steak", 1000.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Dish STEAK_DISH_2 = new Dish(MEAL1_ID + 3, "Machete steak", 700.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Dish VIETNAM_DISH_2 = new Dish(MEAL1_ID + 4, "Pho bo soup", 400.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Dish ITALIAN_DISH_3 = new Dish(MEAL1_ID + 5, "Chicken Parmigiana", 400.00, of(2020, Month.MARCH, 31, 10, 00));
    public static final Dish ITALIAN_DISH_4 = new Dish(MEAL1_ID + 6, "Fried Mozzarella", 750.50, of(2020, Month.MARCH, 31, 10, 00));
    public static final Dish VIETNAM_DISH_3 = new Dish(MEAL1_ID + 7, "Summer Rolls", 600.00, of(2020, Month.MARCH, 31, 10, 00));
    public static final Dish STEAK_DISH_3 = new Dish(MEAL1_ID + 7, "T-bone", 1000.50, of(2020, Month.MARCH, 31, 10, 00));
    public static final Dish STEAK_DISH_4 = new Dish(MEAL1_ID + 8, "Lamb Chops", 700.50, of(2020, Month.MARCH, 31, 10, 00));
    public static final Dish VIETNAM_DISH_4 = new Dish(MEAL1_ID + 9, "Crispy Baby Squid", 400.50, of(2020, Month.MARCH, 31, 10, 00));

    public static final List<Dish> STEAK_MEALS_D_1 = List.of(STEAK_DISH_1, STEAK_DISH_2);
    public static final List<Dish> STEAK_MEALS_D_2 = List.of(STEAK_DISH_3, STEAK_DISH_4);
    public static final List<Dish> ITALIAN_MEALS_D_1 = List.of(ITALIAN_DISH_1, ITALIAN_DISH_2);
    public static final List<Dish> ITALIAN_MEALS_D_2 = List.of(ITALIAN_DISH_3, ITALIAN_DISH_4);
    public static final List<Dish> VIETNAM_MEALS_D_1 = List.of(VIETNAM_DISH_1, VIETNAM_DISH_2);
    public static final List<Dish> VIETNAM_MEALS_D_2 = List.of(VIETNAM_DISH_3, VIETNAM_DISH_4);


}
