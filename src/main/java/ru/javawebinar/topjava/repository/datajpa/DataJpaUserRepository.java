package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.List;


//Интерфесы репозитории не создаю тк только 1 реализация

@Repository
public class DataJpaUserRepository{
    // TODO написать

    @Autowired
    CrudUserRepository crudRepository;

//    @Autowired
//    CrudRestaurantRepository crudRestaurantRepository;


    


}
