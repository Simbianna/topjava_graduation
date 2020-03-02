package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.datajpa.DataJpaMealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MealService {

    private final DataJpaMealRepository repository;

    @Autowired
    public MealService(DataJpaMealRepository repository) {
        this.repository = repository;
    }

    public ru.javawebinar.topjava.model.Meal get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Meal> getAll() {
        return repository.getAll();
    }

    public List<Meal> getAllForRestaurant(int restaurantId){return repository.getAllForRestaurant(restaurantId);}

    //admin command
    public List<Meal> getAllForRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return repository.getAllForRestaurantBetweenDates(startDate, endDate, restaurantId);
    }

    public List<Meal> getForRestaurantForToday(LocalDate today, int restaurantId) {
        return repository.getForRestaurantForToday(today, restaurantId);
    }

    public void update(Meal meal) {
        Assert.notNull(meal, "Meal must not be null");
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    public Meal create(Meal Meal) {
        Assert.notNull(Meal, "Meal must not be null");
        return repository.save(Meal);
    }
    
    


}
