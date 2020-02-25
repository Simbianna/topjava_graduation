package ru.javawebinar.topjava.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.baseEntities.Meal;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MealRepository {

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Transactional
    public Meal save(Meal restaurant, int userId) {
        return null;
    }

    public boolean delete(int id, int userId) {
        return false;
    }

    public Meal get(int id) {
        return new Meal();
    }

    public List<Meal> getAll() {
        return new ArrayList<>();
    }
}
