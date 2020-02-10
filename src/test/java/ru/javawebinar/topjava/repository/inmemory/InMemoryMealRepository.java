package ru.javawebinar.topjava.repository.inmemory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
//TODO исправлены только красные ошибка, переписать реализацию в зависимости от основного кода

@Repository
public class InMemoryMealRepository{
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    // Map  userId -> (mealId-> meal)
    private Map<Integer, InMemoryBaseRepository<Meal>> restaurantsMealsMap = new ConcurrentHashMap<>();

    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal, "meal must not be null");
        InMemoryBaseRepository<Meal> meals = restaurantsMealsMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepository<>());
        return meals.save(meal);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }


    public boolean delete(int id, int userId) {
        InMemoryBaseRepository<Meal> meals = restaurantsMealsMap.get(userId);
        return meals != null && meals.delete(id);
    }


    public Meal get(int id, int userId) {
        InMemoryBaseRepository<Meal> meals = restaurantsMealsMap.get(userId);
        return meals == null ? null : meals.get(id);
    }

    public List<Meal> getAll(int userId) {
        return getAllFiltered(userId, meal -> true);
    }

    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime, "startDateTime must not be null");
        Objects.requireNonNull(endDateTime, "endDateTime must not be null");
        return null; // getAllFiltered(userId, meal -> Util.isBetween(meal.getAdded(), startDateTime, endDateTime));
    }

    private List<Meal> getAllFiltered(int userId, Predicate<Meal> filter) {
        InMemoryBaseRepository<Meal> meals = restaurantsMealsMap.get(userId);
        return meals == null ? Collections.emptyList() :
                meals.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getAdded).reversed())
                        .collect(Collectors.toList());
    }
}