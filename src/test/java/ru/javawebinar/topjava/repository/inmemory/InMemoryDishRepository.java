package ru.javawebinar.topjava.repository.inmemory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.service.DIshService;
import ru.javawebinar.topjava.util.Util;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TODO исправлены только красные ошибка, переписать реализацию в зависимости от основного кода

@Repository
public class InMemoryDishRepository extends InMemoryBaseRepository<Dish> {
    private static final Logger log = LoggerFactory.getLogger(InMemoryDishRepository.class);

    private Map<Integer, InMemoryBaseRepository<Dish>> dishesMap = new ConcurrentHashMap<>();

    public Dish save(Dish dish, int restaurantId) {
        Objects.requireNonNull(dish, "Dish must not be null");
        InMemoryBaseRepository<Dish> dishes = dishesMap.computeIfAbsent(restaurantId, rid -> new InMemoryBaseRepository<>());
        return dishes.save(dish);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    public boolean delete(int id, int restaurantId) {
        InMemoryBaseRepository<Dish> dishes = dishesMap.get(restaurantId);
        return dishes != null && dishes.delete(id);
    }

    public Dish get(int id, int restaurantId) {
        InMemoryBaseRepository<Dish> dishes = dishesMap.get(restaurantId);
        return dishes == null ? null : dishes.get(id);
    }

    public List<Dish> getAll(int restaurantId) {
        return getAllFiltered(restaurantId, dish -> true);
    }

    private List<Dish> getAllFiltered(int restaurantId, Predicate<Dish> filter) {
        InMemoryBaseRepository<Dish> dishes = dishesMap.get(restaurantId);
        return dishes == null ? Collections.emptyList() :
                dishes.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Dish::getAdded).reversed())
                        .collect(Collectors.toList());
    }

    public List<Dish> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int restaurantId){
        Objects.requireNonNull(startDateTime, "startDateTime must not be null");
        Objects.requireNonNull(endDateTime, "endDateTime must not be null");
        return getAllFiltered(restaurantId, dish -> Util.isBetween(dish.getAdded(), startDateTime, endDateTime));
    }

}