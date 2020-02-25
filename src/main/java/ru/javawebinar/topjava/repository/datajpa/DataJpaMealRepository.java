package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class DataJpaMealRepository{

    @Autowired
    CrudMealRepository mealRepository;

    @Autowired
    CrudRestaurantRepository restaurantRepository;

    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(restaurantRepository.getOne(restaurantId));
        return mealRepository.save(meal);
    }

    public boolean delete(int id, int restaurantId) {
        return mealRepository.delete(id, restaurantId) != 0;
    }

    
    public Meal get(int id, int restaurantId) {
        return mealRepository.findById(id).filter(new Predicate<Meal>() {
            @Override
            public boolean test(Meal meal) {
                return meal.getRestaurant().getId() == restaurantId;
            }
        }).orElse(null);
    }

    
    public List<Meal> getAll(int restaurantId) {
        return mealRepository.getAll(restaurantId);
    }

    
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return mealRepository.getBetween(startDate, endDate, restaurantId);
    }

//    public List<Meal> getForToday(int restaurantId) {
//        return mealRepository.getBetween(LocalDate.now().atStartOfDay(),LocalDate.now().plusDays(1).atStartOfDay(), restaurantId);
//    }


    public List<Meal> getForToday(LocalDate today, int restaurantId) {
        return mealRepository.getForToday(today, restaurantId);
    }

    
    public Meal getWithRestaurant(int id, int restaurantId) {
        return mealRepository.getWithRestaurant(id, restaurantId);
    }
}
