package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    @Autowired
    CrudRestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(SORT_NAME);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public boolean delete(int id) {
        return restaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return restaurantRepository.getWithAllDishes(id);
    }

    @Override
    public List<Restaurant> getAllWithDishes() {
        return restaurantRepository.getAllWithAllDishes();
    }

    @Override
    public Restaurant getWithActualMenu(int id) {
        return restaurantRepository.getWithActualMenu(id);
    }

    @Override
    public List<Restaurant> getAllWithActualMenu() {
        return restaurantRepository.getAllWithActualMenu();
    }

}
