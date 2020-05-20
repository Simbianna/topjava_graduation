package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Dish;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaDishRepository {
    private static final Sort SORT_BY_ID = new Sort(Sort.Direction.DESC, "id");

    @Autowired
    CrudDishRepository dishRepository;

    //admin command
    @Transactional
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    //admin command
    @Transactional
    public boolean delete(int id) {
        return dishRepository.delete(id) != 0;
    }

    public Dish get(int id) {
        return dishRepository.findById(id).orElse(null);
    }

    public List<Dish> getAll() {
        return dishRepository.findAll(SORT_BY_ID);
    }

    public List<Dish> getAllForRestaurant(int restaurantId) {
        return dishRepository.getAll(restaurantId);
    }

    public List<Dish> getAllForRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return dishRepository.getAllBetween(restaurantId, startDate, endDate);
    }

//    public List<Dish> getAllForRestaurantForToday(LocalDateTime start today, int restaurantId) {
//        return dishRepository.getDishesByRestaurantForToday(today, restaurantId);
//    }

    public Dish getWithRestaurant(int id) {
        return dishRepository.getWithRestaurant(id);
    }

    //    public List<Dish> getForToday(int restaurantId) {
//        return mealRepository.getBetween(LocalDate.now().atStartOfDay(),LocalDate.now().plusDays(1).atStartOfDay(), restaurantId);
//    }
}
