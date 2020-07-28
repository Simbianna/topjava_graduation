package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.model.Restaurant;
import ru.javawebinar.topjava.repository.MenuRepository;
import ru.javawebinar.topjava.repository.RestaurantRepository;
import ru.javawebinar.topjava.repository.datajpa.CrudRestaurantRepository;
import ru.javawebinar.topjava.util.exception.IllegalRequestDataException;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final CrudRestaurantRepository restaurantRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, CrudRestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    public Menu getById(int id) {
        return checkNotFoundWithId(menuRepository.getById(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id), id);
    }

    public void update(Menu menu) {
        Assert.notNull(menu, "Menu must not be null");
        checkNotFoundWithId(menuRepository.update(menu), menu.getId());
    }

    public Menu create(Menu menu) {
        Assert.notNull(menu, "Menu must not be null");
        Restaurant restaurant = menu.getRestaurant();
       if (restaurant == null) throw new IllegalArgumentException(" restaurant is NULL");
       if (restaurantRepository.getWithMenu(restaurant.getId()) != null ){
           throw new IllegalArgumentException(restaurant + " restaurant already has a menu!");
       }
        return menuRepository.create(menu);
    }

}
