package ru.javawebinar.topjava.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.testData.DishTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javawebinar.topjava.testData.RestaurantTestData.*;
import static ru.javawebinar.topjava.testData.UserTestData.ADMIN_ID;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private JpaUtil jpaUtil;

    @BeforeEach
    void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

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
    void getAll() throws Exception {
        assertMatch(service.getAll(), ALL_RESTAURANTS);
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getRussianCreated();
        Restaurant created = service.create(newRestaurant, ADMIN_ID);
        newRestaurant.setId(created.getId());
        assertMatch(newRestaurant, created);
        assertMatch(service.getAll(), STEAK_HOUSE, ITALIAN, VIETNAM, RUSSIAN);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getItalianUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(ITALIAN_ID), updated);
    }

    @Test
    void getWithDishes() throws Exception {
        Restaurant italian = service.getWithDishes(ITALIAN_ID);
        assertMatch(italian, ITALIAN);
        DishTestData.assertMatch(italian.getDishes(),DishTestData.ITALIAN_DISHES_SORTED_BY_DT);
    }

    @Test
    void getWithDishesNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getWithDishes(1));
    }
}