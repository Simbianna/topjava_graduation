package ru.javawebinar.topjava.testData;


import ru.javawebinar.topjava.model.Meal;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.*;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 5;

    public static final Meal ITALIAN_MEAL1 = new Meal(MEAL1_ID, "Daily pizza", 500.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Meal ITALIAN_MEAL2 = new Meal(MEAL1_ID, "Roasted salmon", 700.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Meal VIETNAM_MEAL1 = new Meal(MEAL1_ID + 1, "Grilled shrimps", 600.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Meal STEAK_MEAL1 = new Meal(MEAL1_ID + 2, "Ribeye steak", 1000.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Meal STEAK_MEAL2 = new Meal(MEAL1_ID + 3, "Machete steak", 700.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Meal VIETNAM_MEAL2 = new Meal(MEAL1_ID + 4, "Pho bo soup", 400.00, of(2020, Month.MARCH, 30, 10, 00));
    public static final Meal ITALIAN_MEAL3 = new Meal(MEAL1_ID + 5, "Chicken Parmigiana", 400.00, of(2020, Month.MARCH, 31, 10, 00));
    public static final Meal ITALIAN_MEAL4 = new Meal(MEAL1_ID + 6, "Fried Mozzarella", 750.50, of(2020, Month.MARCH, 31, 10, 00));
    public static final Meal VIETNAM_MEAL3 = new Meal(MEAL1_ID + 7, "Summer Rolls", 600.00, of(2020, Month.MARCH, 31, 10, 00));
    public static final Meal STEAK_MEAL3 = new Meal(MEAL1_ID + 7, "T-bone", 1000.50, of(2020, Month.MARCH, 31, 10, 00));
    public static final Meal STEAK_MEAL4 = new Meal(MEAL1_ID + 8, "Lamb Chops", 700.50, of(2020, Month.MARCH, 31, 10, 00));
    public static final Meal VIETNAM_MEAL4 = new Meal(MEAL1_ID + 9, "Crispy Baby Squid", 400.50, of(2020, Month.MARCH, 31, 10, 00));

    public static final List<Meal> STEAK_MEALS_D1 = List.of(STEAK_MEAL1, STEAK_MEAL2);
    public static final List<Meal> STEAK_MEALS_D2 = List.of(STEAK_MEAL3, STEAK_MEAL4);
    public static final List<Meal> ITALIAN_MEALS_D1 = List.of(ITALIAN_MEAL1, ITALIAN_MEAL2);
    public static final List<Meal> ITALIAN_MEALS_D2 = List.of(ITALIAN_MEAL3, ITALIAN_MEAL4);
    public static final List<Meal> VIETNAM_MEALS_D1 = List.of(VIETNAM_MEAL1, VIETNAM_MEAL2);
    public static final List<Meal> VIETNAM_MEALS_D2 = List.of(VIETNAM_MEAL3, VIETNAM_MEAL4);


}
