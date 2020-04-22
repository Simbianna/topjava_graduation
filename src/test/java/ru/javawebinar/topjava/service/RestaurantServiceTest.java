package ru.javawebinar.topjava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.topjava.testData.RestaurantTestData.*;
import static ru.javawebinar.topjava.testData.UserTestData.ADMIN_ID;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    void get() throws Exception {
        assertMatch(service.get(ITALIAN_ID), ITALIAN);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void delete() throws Exception {
        service.delete(ITALIAN_ID, ADMIN_ID);
        System.out.println("done");
        List<Restaurant> restaurants = service.getAll();
        System.out.println("done");
        assertMatch(restaurants, STEAK_HOUSE, VIETNAM);
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1, ADMIN_ID));
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getRussianCreated();
        Restaurant created = service.create(newRestaurant, ADMIN_ID);
        newRestaurant.setId(created.getId());
        assertMatch(newRestaurant, created);
        assertMatch(service.getAll(),STEAK_HOUSE, ITALIAN, VIETNAM, RUSSIAN) ;
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getItalianUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(ITALIAN_ID),updated);
    }

    @Test
    void getAll() throws Exception {
        assertMatch(service.getAll(), ALL_RESTAURANTS);
    }

    @Test
    void getWithMenu() throws Exception {
    }
}