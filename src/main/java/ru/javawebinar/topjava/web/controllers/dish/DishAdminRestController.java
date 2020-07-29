package ru.javawebinar.topjava.web.controllers.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.Dish;
import ru.javawebinar.topjava.repository.DishRepository;


import java.net.URI;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

//TODO возможно добавить возможность смотреть  актуальную еду
@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController {
    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DishRepository dishRepository;

    @Autowired
    public DishAdminRestController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        log.info("get all dishes for restaurant {}", restaurantId);
        return dishRepository.getAll(restaurantId);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish {}", id);
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createResponseEntity(@Validated(View.Web.class) @RequestBody Dish dish) {
        log.info("create {}", dish);
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish {}", id);
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int id) {
        log.info("update dish {}", dish);
        Assert.notNull(dish, "Dish must not be null");
        assureIdConsistent(dish, id);
        checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    @Transactional
    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void includeInActualMenu(@PathVariable int id, @RequestParam boolean included) {
        log.info(included ? "include {}" : "exclude {}", id);
        Dish dish = checkNotFoundWithId(dishRepository.get(id), id);
        dish.setIncluded(included);
     /*   dishRepository.save(dish);*///only for jdbc
    }
}
