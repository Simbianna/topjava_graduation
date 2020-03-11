package ru.javawebinar.topjava.repository.inmemory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
//TODO исправлены только красные ошибка, переписать реализацию в зависимости от основного кода

@Repository
public class InMemoryMealRepository extends InMemoryBaseRepository<Meal>{
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);


}