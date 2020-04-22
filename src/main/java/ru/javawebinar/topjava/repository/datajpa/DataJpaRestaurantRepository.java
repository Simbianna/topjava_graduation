package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository {

//    @Autowired
//    CrudUserRepository crudUserRepository;

    @Autowired
    CrudRestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public boolean delete(int id) {
        return restaurantRepository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll()  ;
    }

    public Restaurant getWithMeals(int id) {
        return restaurantRepository.getWithMeals(id);
    }
}
