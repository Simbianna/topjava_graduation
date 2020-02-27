package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class DataJpaMealRepository{
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    CrudMealRepository mealRepository;

    //admin command
    public Meal save(Meal meal){return mealRepository.save(meal);}

    //admin command
    public boolean delete(int id){return mealRepository.delete(id)!=0;}

    public Meal get(int id){return mealRepository.findById(id).orElse(null);}

    //admin command
    public List<Meal> getAll(){return mealRepository.findAll(SORT_NAME);}

    public List<Meal> getAllForRestaurant(int restaurantId){return mealRepository.getMealsByRestaurant_Id(restaurantId);}

    //admin command
    public List<Meal> getAllForRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return mealRepository.getMealsByAddedBetweenAndRestaurant_Id(startDate, endDate, restaurantId);
    }

    public List<Meal> getForRestaurantForToday(LocalDate today, int restaurantId) {
        return mealRepository.getMealsByRestaurantForToday(today, restaurantId);
    }


//    public List<Meal> getForToday(int restaurantId) {
//        return mealRepository.getBetween(LocalDate.now().atStartOfDay(),LocalDate.now().plusDays(1).atStartOfDay(), restaurantId);
//    }


    public Meal getWithRestaurant(int id) {
        return mealRepository.getWithRestaurant(id);
    }
}
