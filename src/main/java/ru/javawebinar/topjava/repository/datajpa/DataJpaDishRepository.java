package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {
    private static final Sort SORT_BY_ID = Sort.by(Sort.Direction.DESC, "id");

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

    //for restaurant
    public List<Dish> getAll(int restaurantId) {
        return dishRepository.getAll(restaurantId);
    }

   /* public List<Dish> getBetween(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
       return  null;
        // return dishRepository.getBetween(restaurantId, startDate, endDate);
    }

    public Dish getWithRestaurant(int id, int restaurantId) {
        return dishRepository.getWithRestaurant(id, restaurantId);
    }*/
//    public List<Dish> getAllForRestaurantForToday(LocalDateTime start today, int restaurantId) {
//        return dishRepository.getDishesByRestaurantForToday(today, restaurantId);
//    }

//    public List<Dish> getAll() {
//        return dishRepository.findAll(SORT_BY_ID);
//    }

//    public List<Dish> getForToday(int restaurantId) {
//        return mealRepository.getBetween(LocalDate.now().atStartOfDay(),LocalDate.now().plusDays(1).atStartOfDay(), restaurantId);
//    }
}
