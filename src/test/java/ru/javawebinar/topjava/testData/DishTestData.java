package ru.javawebinar.topjava.testData;


import ru.javawebinar.topjava.model.Dish;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.*;
import static ru.javawebinar.topjava.testData.DataCollectorUtil.*;
import static ru.javawebinar.topjava.testData.RestaurantTestData.*;

public class DishTestData {
    public static final int DISH1_ID = START_SEQ + 5;
//    private static final Comparator<Dish> SORT_DISHES_BY_DATETIME = (o1, o2) -> o2.getAdded().compareTo(o1.getAdded());
    private static final Comparator<Dish> SORT_DISHES_BY_ID = (o1, o2) -> o2.getId().compareTo(o1.getId());

   /* public static final Dish ITALIAN_DISH_3 = new Dish(DISH1_ID + 6, "Chicken Parmigiana", 400.00, of(2020, Month.MARCH, 31, 10, 0));
    public static final Dish ITALIAN_DISH_4 = new Dish(DISH1_ID + 7, "Fried Mozzarella", 750.50, of(2020, Month.MARCH, 31, 10, 0));
    public static final Dish VIETNAM_DISH_3 = new Dish(DISH1_ID + 8, "Summer Rolls", 600.00, of(2020, Month.MARCH, 31, 10, 0));
    public static final Dish STEAK_DISH_3 = new Dish(DISH1_ID + 9, "T-bone", 1000.50, of(2020, Month.MARCH, 31, 10, 0));
    public static final Dish STEAK_DISH_4 = new Dish(DISH1_ID + 10, "Lamb Chops", 700.50, of(2020, Month.MARCH, 31, 10, 0));
    public static final Dish VIETNAM_DISH_4 = new Dish(DISH1_ID + 11, "Crispy Baby Squid", 400.50, of(2020, Month.MARCH, 31, 10, 0));
    public static final Dish ITALIAN_DISH_1 = new Dish(DISH1_ID, "Daily pizza", 500.00, of(2020, Month.MARCH, 30, 10, 0));
    public static final Dish ITALIAN_DISH_2 = new Dish(DISH1_ID + 1, "Roasted salmon", 700.00, of(2020, Month.MARCH, 30, 0, 0));
    public static final Dish VIETNAM_DISH_1 = new Dish(DISH1_ID + 2, "Grilled shrimps", 600.00, of(2020, Month.MARCH, 30, 23, 59));
    public static final Dish STEAK_DISH_1 = new Dish(DISH1_ID + 3, "Ribeye steak", 1000.00, of(2020, Month.MARCH, 30, 10, 0));
    public static final Dish STEAK_DISH_2 = new Dish(DISH1_ID + 4, "Machete steak", 700.00, of(2020, Month.MARCH, 30, 10, 0));
    public static final Dish VIETNAM_DISH_2 = new Dish(DISH1_ID + 5, "Pho bo soup", 400.00, of(2020, Month.MARCH, 30, 10, 0));
    public static final Dish ITALIAN_DISH_CREATED = new Dish(100021, "new Italian Dish", 100.00, LocalDateTime.of(2020, Month.MARCH, 1, 10, 0));
    public static final Dish ITALIAN_DISH_TO_DELETE = ITALIAN_DISH_1;*/

    //    public static final Dish NONEXISTING_DISH = new Dish(null, "New", 0.00, LocalDateTime.now(), ITALIAN);
/*
    public static final List<Dish> STEAK_DISHES_D_1 = List.of(STEAK_DISH_1, STEAK_DISH_2);
    public static final List<Dish> STEAK_DISHES_D_2 = List.of(STEAK_DISH_3, STEAK_DISH_4);
    public static final List<Dish> ITALIAN_DISHES_D_1 = List.of(ITALIAN_DISH_1, ITALIAN_DISH_2);
    public static final List<Dish> ITALIAN_DISHES_D_2 = List.of(ITALIAN_DISH_3, ITALIAN_DISH_4);
    public static final List<Dish> VIETNAM_DISHES_D_1 = List.of(VIETNAM_DISH_1, VIETNAM_DISH_2);
    public static final List<Dish> VIETNAM_DISHES_D_2 = List.of(VIETNAM_DISH_3, VIETNAM_DISH_4);*/

    //    public static final List<Dish> ALL_DISHES = collectEntities(STEAK_DISHES_D_1, STEAK_DISHES_D_2, ITALIAN_DISHES_D_1, ITALIAN_DISHES_D_2, VIETNAM_DISHES_D_1, VIETNAM_DISHES_D_2);
  /*  public static final List<Dish> ALL_DISHES_SORTED_BY_DT = collectEntitiesSorted(SORT_DISHES_BY_DATETIME, STEAK_DISHES_D_1, STEAK_DISHES_D_2, ITALIAN_DISHES_D_1, ITALIAN_DISHES_D_2, VIETNAM_DISHES_D_1, VIETNAM_DISHES_D_2);
    //    public static final List<Dish> STEAK_DISHES = collectEntities(STEAK_DISHES_D_1, STEAK_DISHES_D_2);
    public static final List<Dish> STEAK_DISHES_SORTED_BY_DT = collectEntitiesSorted(SORT_DISHES_BY_DATETIME, STEAK_DISHES_D_1, STEAK_DISHES_D_2);
    public static final List<Dish> ITALIAN_DISHES = collectEntities(ITALIAN_DISHES_D_1, ITALIAN_DISHES_D_2);
    public static final List<Dish> ITALIAN_DISHES_SORTED_BY_DT = getEntitiesSorted(ITALIAN_DISHES, SORT_DISHES_BY_DATETIME);
    public static final List<Dish> ITALIAN_DISHES_SORTED_BY_DT_WITH_CREATED = collectEntitiesSorted(SORT_DISHES_BY_DATETIME, ITALIAN_DISHES, ITALIAN_DISH_CREATED);
    public static final List<Dish> ITALIAN_DISHES_SORTED_BY_DT_EXCEPT_DELETED = collectEntitiesSortedExceptOne(ITALIAN_DISHES, ITALIAN_DISH_TO_DELETE, SORT_DISHES_BY_DATETIME);

*/
  /*  public static Dish getItalianCreated() {
        return new Dish(null, "new Italian Dish", 100.00, LocalDateTime.of(2020, Month.MARCH, 1, 10, 0), ITALIAN);
    }

    public static Dish getItalianDishUpdated() {
        return new Dish(DISH1_ID, "New", 100.00, of(2020, Month.MARCH, 30, 10, 0), ITALIAN);
    }*/

    public static List<Dish> getDishesSortedByID(List<Dish> dishes) {
        return getEntitiesSorted(dishes, SORT_DISHES_BY_ID);

//    public static final List<Dish> VIETNAM_DISHES = collectEntities(VIETNAM_DISHES_D_1, VIETNAM_DISHES_D_2);

        //    public static List<Dish> getDishesSortedByDate(List<Dish> dishes){
//        return getEntitiesSorted(dishes, SORT_BY_DATETIME);
//    }
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

//    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
//        assertMatch(actual, List.of(expected));
//    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

   /* public static final Dish ITALIAN_DISH_3 = new Dish(DISH1_ID + 6, "Chicken Parmigiana", 400.00, of(2020, Month.MARCH, 31, 10, 0), ITALIAN);
    public static final Dish ITALIAN_DISH_4 = new Dish(DISH1_ID + 7, "Fried Mozzarella", 750.50, of(2020, Month.MARCH, 31, 10, 0), ITALIAN);
    public static final Dish VIETNAM_DISH_3 = new Dish(DISH1_ID + 8, "Summer Rolls", 600.00, of(2020, Month.MARCH, 31, 10, 0), VIETNAM);
    public static final Dish STEAK_DISH_3 = new Dish(DISH1_ID + 9, "T-bone", 1000.50, of(2020, Month.MARCH, 31, 10, 0), STEAK_HOUSE);
    public static final Dish STEAK_DISH_4 = new Dish(DISH1_ID + 10, "Lamb Chops", 700.50, of(2020, Month.MARCH, 31, 10, 0), STEAK_HOUSE);
    public static final Dish VIETNAM_DISH_4 = new Dish(DISH1_ID + 11, "Crispy Baby Squid", 400.50, of(2020, Month.MARCH, 31, 10, 0), VIETNAM);
    public static final Dish ITALIAN_DISH_1 = new Dish(DISH1_ID, "Daily pizza", 500.00, of(2020, Month.MARCH, 30, 10, 0), ITALIAN);
    public static final Dish ITALIAN_DISH_2 = new Dish(DISH1_ID + 1, "Roasted salmon", 700.00, of(2020, Month.MARCH, 30, 0, 0), ITALIAN);
    public static final Dish VIETNAM_DISH_1 = new Dish(DISH1_ID + 2, "Grilled shrimps", 600.00, of(2020, Month.MARCH, 30, 23, 59), VIETNAM);
    public static final Dish STEAK_DISH_1 = new Dish(DISH1_ID + 3, "Ribeye steak", 1000.00, of(2020, Month.MARCH, 30, 10, 0), STEAK_HOUSE);
    public static final Dish STEAK_DISH_2 = new Dish(DISH1_ID + 4, "Machete steak", 700.00, of(2020, Month.MARCH, 30, 10, 0), STEAK_HOUSE);
    public static final Dish VIETNAM_DISH_2 = new Dish(DISH1_ID + 5, "Pho bo soup", 400.00, of(2020, Month.MARCH, 30, 10, 0), VIETNAM);
    public static final Dish ITALIAN_DISH_CREATED = new Dish(100021, "new Italian Dish", 100.00, LocalDateTime.of(2020, Month.MARCH, 1, 10, 0), ITALIAN);
    public static final Dish ITALIAN_DISH_TO_DELETE = ITALIAN_DISH_1;*/

}
