package ru.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dto.DishTo;
import ru.model.Dish;
import ru.model.Restaurant;
import ru.repository.DishRepository;
import ru.repository.RestaurantRepository;
import ru.util.ValidationUtil;
import ru.util.toUtil.DishToDtoTransformer;

import java.util.List;


@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final ValidationUtil validationUtil;
    private final DishToDtoTransformer dishTransformer;
    private final RestaurantRepository restaurantRepository;

    @Override
    public Dish getByIdForRestaurant(int dishId, int restaurantId) {
        return validationUtil.checkNotFoundWithId(dishRepository.findByIdAndRestaurantId(dishId, restaurantId), dishId);
    }

    @Override
    public DishTo getByIdForRestaurantAsDto(int dishId, int restaurantId) {
        return dishTransformer.asTo(validationUtil.checkNotFoundWithId(dishRepository.findByIdAndRestaurantId(dishId, restaurantId), dishId));
    }

    @Override
    public Dish createForRestaurant(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        validationUtil.checkNew(dish);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> getAllByRestaurantId(int restaurantId) {
        return dishRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public void deleteByIdForRestaurant(int dishId, int restaurantId) {
        dishRepository.deleteByIdAndRestaurantId(dishId, restaurantId);
    }

    @Override
    public List<Dish> getAllIncludedByRestaurantId(int restaurantId) {
        return dishRepository.findAllIncludedByRestaurantId(restaurantId);
    }

    @Override
    public Dish updateForRestaurant(Dish dish, int restaurantId, int dishId) {
        Assert.notNull(dish, "Dish must not be null");
        validationUtil.assureIdConsistent(dish, dishId);
        dish.setAdded(dishRepository.findByIdAndRestaurantId(dishId, restaurantId).getAdded());
        Restaurant restaurant = validationUtil.checkNotFoundWithId(restaurantRepository.getById(restaurantId), restaurantId);
        dish.setRestaurant(restaurant);
        return validationUtil.checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    @Override
    public void includeInOrExcludeFromActualMenu(int restaurantId, int dishId, boolean isIncluded) {
        Dish dish = validationUtil.checkNotFoundWithId(dishRepository.findByIdAndRestaurantId(dishId, restaurantId), dishId);
        dish.setIncluded(isIncluded);
        dish.setRestaurant(restaurantRepository.getById(restaurantId));
        validationUtil.checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    @Override
    public List<DishTo> getAllIncludedByRestaurantIdDto(int restaurantId) {
        return dishTransformer.asToList(getAllIncludedByRestaurantId(restaurantId));
    }
}
