package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Dish;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaDishRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    CrudDishRepository mealRepository;

    //admin command
    public Dish save(Dish dish){return mealRepository.save(dish);}

    //admin command
    public boolean delete(int id){return mealRepository.delete(id)!=0;}

    public Dish get(int id){return mealRepository.findById(id).orElse(null);}

    //admin command
    public List<Dish> getAll(){return mealRepository.findAll(SORT_NAME);}

    public List<Dish> getAllForRestaurant(int restaurantId){return mealRepository.getMealsByRestaurant_Id(restaurantId);}

    //admin command
    public List<Dish> getAllForRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return mealRepository.getMealsByAddedBetweenAndRestaurant_Id(startDate, endDate, restaurantId);
    }

    public List<Dish> getForRestaurantForToday(LocalDate today, int restaurantId) {
        return mealRepository.getMealsByRestaurantForToday(today, restaurantId);
    }


//    public List<Dish> getForToday(int restaurantId) {
//        return mealRepository.getBetween(LocalDate.now().atStartOfDay(),LocalDate.now().plusDays(1).atStartOfDay(), restaurantId);
//    }


    public Dish getWithRestaurant(int id) {
        return mealRepository.getWithRestaurant(id);
    }
}
