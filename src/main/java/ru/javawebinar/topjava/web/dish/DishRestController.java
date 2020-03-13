package ru.javawebinar.topjava.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.service.DIshService;

public class DishRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DIshService service;

    public DishRestController(DIshService service) {
        this.service = service;
    }

    public Dish get(int id) {
        log.info("get meal {}", id);
        return service.get(id);
    }

    public void delete(int id){
        log.info("delete meal {}", id);
       service.delete(id);
    }



}
