package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//Интерфесы репозитории не создаю тк только 1 реализация

@Repository
public class DataJpaUserRepository{
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    CrudUserRepository userRepository;

    public User save(User user) {

        return userRepository.save(user);
    }

    public boolean delete(int id) {
        return userRepository.delete(id) != 0;
    }

    public User get(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }

    public User getWithVotes(int id) {
        return userRepository.getWithVotes(id);
    }

//    public User getWithLastVote(int id) { return userRepository.getWithLastVote(id);
//    }
}
