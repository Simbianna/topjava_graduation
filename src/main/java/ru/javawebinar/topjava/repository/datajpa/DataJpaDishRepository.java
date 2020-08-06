package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.repository.DishRepository;

import java.util.List;
import java.util.Set;

@Repository
public class DataJpaDishRepository implements DishRepository {

    @Autowired
    CrudDishRepository dishRepository;

    @Autowired
    CrudRestaurantRepository restaurantRepository;

    @Override
    public List<Dish> getAllByRestaurantId(int restaurantId) {
        return dishRepository.getAllByRestaurantId(restaurantId);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return dishRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return dishRepository.delete(id, restaurantId) != 0;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> getAllIncludedByRestaurantId(int restaurantId) {
        return dishRepository.getAllIncludedByRestaurantId(restaurantId);
    }

}
