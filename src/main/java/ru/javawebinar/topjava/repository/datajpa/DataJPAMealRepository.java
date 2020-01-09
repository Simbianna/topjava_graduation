package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.baseEntities.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.datajpa.CrudMealRepository;
import ru.javawebinar.topjava.repository.datajpa.CrudRestaurantRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class DataJPAMealRepository implements MealRepository{

    @Autowired
    CrudMealRepository crudMealRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return crudMealRepository.findById(id).filter(meal -> meal.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return crudMealRepository.getAll(restaurantId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return crudMealRepository.getBetween(startDate, endDate, restaurantId);
    }

    @Override
    public List<Meal> getForToday(LocalDate today, int restaurantId) {
        return crudMealRepository.getForToday(today, restaurantId);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return crudMealRepository.getWithRestaurant(id, restaurantId);
    }
}
