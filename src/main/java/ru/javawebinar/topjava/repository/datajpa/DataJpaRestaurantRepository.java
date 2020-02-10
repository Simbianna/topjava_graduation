package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository{

//    @Autowired
//    CrudUserRepository crudUserRepository;

    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        return null;
    }

    public boolean delete(int id, int userId) {
        return false;
    }

    public Restaurant get(int id) {
        return new Restaurant();
    }

    public List<Restaurant> getAll() {
        return new ArrayList<>();
    }
}
