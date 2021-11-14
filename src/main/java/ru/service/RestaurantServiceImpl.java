package ru.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dto.RestaurantTo;
import ru.model.Restaurant;
import ru.repository.RestaurantRepository;
import ru.util.ValidationUtil;
import ru.util.toUtil.RestaurantToDtoTransformer;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ValidationUtil validator;
    private final RestaurantToDtoTransformer restaurantTransformer;


    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant getById(int id) {
        return validator.checkNotFoundWithId(restaurantRepository.findById(id).orElseGet(null), id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        validator.checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, int id) {
        Assert.notNull(restaurant, "restaurant must not be null");
        validator.assureIdConsistent(restaurant, id);
        return validator.checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @Override
    public List<RestaurantTo> getAllAsTo() {
        return restaurantTransformer.asToListForUser(restaurantRepository.findAll());
    }

    @Override
    public RestaurantTo getByIdAsTo(int id) {
        return restaurantTransformer.asToForUser(
                validator.checkNotFoundWithId(restaurantRepository.findById(id).orElseGet(null), id));
    }
}
