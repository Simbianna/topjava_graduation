package ru.javawebinar.topjava.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.baseEntities.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    // TODO delete class

    @Autowired
    CrudUserRepository crudUserRepository;

//    @Autowired
//    CrudRestaurantRepository crudRestaurantRepository;

    
    public User save(User user) {
        return null;
    }

    public boolean delete(int id) {
        return false;
    }

    public User get(int id) {
        return new User();
    }

    public User getByEmail(String email) {
        return new User();
    }

    public List<User> getAll() {
        return new ArrayList<>();
    }


}
