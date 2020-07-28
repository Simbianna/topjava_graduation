package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> getAll();

    // null if not found
    Menu getById(int id);

    // null if not found, when created
    Menu create(Menu menu);
    
    // null if not found, when updated
    Menu update(Menu menu);

    // false if not found
    boolean delete(int id);

}
