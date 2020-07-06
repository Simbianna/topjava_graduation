package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class InMemoryRestaurantRepository extends InMemoryBaseRepository<Restaurant> implements RestaurantRepository {
//    public void init() {
//        entryMap.clear();
//        entryMap.put(UserTestData.USER_ID, USER);
//        entryMap.put(UserTestData.ADMIN_ID, ADMIN);
//    }

    public List<Restaurant> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return null;
    }

    @Override
    public Restaurant getWithRating(int id) {
        return null;
    }

   /* @Override
    public Restaurant gerWithDishesAndRating(int id) {
        return null;
    }*/
}
