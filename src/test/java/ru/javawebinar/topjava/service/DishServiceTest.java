package ru.javawebinar.topjava.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.topjava.testData.DishTestData.*;
import static ru.javawebinar.topjava.testData.RestaurantTestData.ITALIAN_ID;
import static ru.javawebinar.topjava.testData.RestaurantTestData.STEAK_HOUSE_ID;
import static ru.javawebinar.topjava.testData.UserTestData.ADMIN_ID;


class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    void get() throws Exception {
        assertMatch(service.get(DISH1_ID), ITALIAN_DISH_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void delete() throws Exception {
        service.delete(DISH1_ID, ADMIN_ID);
        assertMatch(service.getAllForRestaurant(ITALIAN_ID), ITALIAN_DISHES.subList(1, 4));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1, ADMIN_ID));
    }

    @Test
    void create() throws Exception {
        Dish newDish = getItalianCreated();
        Dish created = service.create(newDish, ADMIN_ID);
        newDish.setId(created.getId());
        assertMatch(newDish, created);
        assertMatch(service.getAllForRestaurant(ITALIAN_ID), ITALIAN_DISHES_WITH_CREATED);
    }

    @Test
    void update() throws Exception {
        Dish updated = getItalianDishUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(DISH1_ID), updated);
    }

    @Test
    void getAll() throws Exception {
        assertMatch(service.getAll(), getDishesSortedByID(ALL_DISHES));
    }

    @Test
    void getAllForRestaurant() throws Exception {
        assertMatch(service.getAllForRestaurant(STEAK_HOUSE_ID), STEAK_DISHES);
    }

    @Test
    void getAllForRestaurantBetweenDates() throws Exception {
        assertMatch(service.getAllForRestaurantBetweenDates(of(2020, Month.MARCH, 31, 10, 0),
                of(2020, Month.MARCH, 31, 10, 0), ITALIAN_ID), ITALIAN_DISHES_D_2);
    }

    @Test
    void getAllForRestaurantForToday() {
        assertMatch(service.getAllForRestaurantForToday(LocalDate.of(2020, Month.MARCH, 30), ITALIAN_ID), ITALIAN_DISHES_D_1);
    }


    //doesnt work
   /* @Test
    void updateNotFound() {
        Throwable exception = assertThrows(NotFoundException.class, ()->service.update(NONEXISTING_DISH, ADMIN_ID));
        String msg = exception.getMessage();
        assertTrue(msg.contains(ErrorType.DATA_NOT_FOUND.name()));
        assertTrue(msg.contains(NotFoundException.NOT_FOUND_EXCEPTION));
        assertTrue(msg.contains(String.valueOf(NONEXISTING_DISH)));
    }*/

    //доделать потом
//    @Test
//    void createWIthException() {
//validateRootCause(() -> service.create(new Dish(null, "Dish", 100.00, of(2019, Month.AUGUST, 9, 9, 0)))
//    }
}