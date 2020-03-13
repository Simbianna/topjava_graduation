package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.repository.datajpa.DataJpaDishRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class DIshService {

    private final DataJpaDishRepository repository;

    @Autowired
    public DIshService(DataJpaDishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Dish> getAll() {
        return repository.getAll();
    }

    public List<Dish> getAllForRestaurant(int restaurantId){return repository.getAllForRestaurant(restaurantId);}

    //admin command
    public List<Dish> getAllForRestaurantBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int restaurantId) {
        return repository.getAllForRestaurantBetweenDates(startDate, endDate, restaurantId);
    }

    public List<Dish> getForRestaurantForToday(LocalDate today, int restaurantId) {
        return repository.getForRestaurantForToday(today, restaurantId);
    }

    public void update(Dish dish) {
        Assert.notNull(dish, "Dish must not be null");
        checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    public Dish create(Dish Dish) {
        Assert.notNull(Dish, "Dish must not be null");
        return repository.save(Dish);
    }
    
    


}
