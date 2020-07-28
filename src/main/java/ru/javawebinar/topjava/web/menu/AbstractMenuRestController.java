package ru.javawebinar.topjava.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.service.MenuService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractMenuRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService menuService;

    public List<Menu> getAll() {
        log.info("get all menus");
        return menuService.getAll();
    }

    public Menu get(int id) {
        log.info("get menu {}", id);
        return menuService.getById(id);
    }

    public Menu create(Menu menu) {
        log.info("create {}", menu);
        checkNew(menu);
        return menuService.create(menu);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        menuService.delete(id);
    }

    public void update(Menu menu, int id) {
        log.info("update {} with id = {}", menu, id);
        assureIdConsistent(menu, id);
        menuService.update(menu);
    }


}
