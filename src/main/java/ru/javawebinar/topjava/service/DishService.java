package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.repository.datajpa.DataJpaDishRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getDaysBeginning;
import static ru.javawebinar.topjava.util.DateTimeUtil.getDaysEnd;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class DishService {

    private final DataJpaDishRepository dishRepository;

    @Autowired
    public DishService(DataJpaDishRepository repository) {
        this.dishRepository = repository;
    }

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    public void update(Dish dish, int userId) {
        Assert.notNull(dish, "Dish must not be null");
        checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    public List<Dish> getAll() {
        return dishRepository.getAll();
    }

    //If needed
    public List<Dish> getAllForRestaurant(int restaurantId) {
        return dishRepository.getAllForRestaurant(restaurantId);
    }
    //admin command

    public List<Dish> getAllForRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return dishRepository.getAllForRestaurantBetweenDates(startDate, endDate, restaurantId);
    }

    public List<Dish> getAllForRestaurantForToday(LocalDate today, int restaurantId) {
        return dishRepository.getAllForRestaurantBetweenDates(getDaysBeginning(today), getDaysEnd(today), restaurantId);
    }

    public Dish create(Dish Dish, int UserId) {
        Assert.notNull(Dish, "Dish must not be null");
        return dishRepository.save(Dish);
    }

    public Dish getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(dishRepository.getWithRestaurant(id, restaurantId), id);
    }


}
