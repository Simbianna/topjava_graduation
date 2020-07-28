package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.repository.MenuRepository;


import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {

    @Autowired
    private CrudMenuRepository menuRepository;

   /* @Autowired
    private CrudRestaurantRepository restaurantRepository;*/

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getById(int id) {
        return menuRepository.getWithDishes(id);
    }

    @Transactional
    @Override
    public Menu update(Menu menu) {
        if (!menu.isNew() && menuRepository.getWithDishesAndMenu(menu.getId()) == null) {
            return null;
        }
        return menuRepository.save(menu);
    }

    @Transactional
    @Override
    public Menu create(Menu menu) {
        if (!menu.isNew() && menuRepository.getWithDishesAndMenu(menu.getId()) == null) {
            return null;
        }
        return menuRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return menuRepository.delete(id) != 0;
    }

    /*public List<Menu> getAllWithDishes() {
        return menuRepository.getAllWithDishes();

*/
   /* @Override
    public Menu getById(int id) {
        return menuRepository.getWithDishes(id);
    }*/
    }
