package ru.javawebinar.topjava.testData;

import ru.javawebinar.topjava.HasId;
import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.Vote;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCollectorUtil {
    private static final Comparator<HasId> SORT_BY_ID_DESC = (o1, o2) -> o2.getId().compareTo(o1.getId());

    @SafeVarargs
    static <T extends AbstractBaseEntity> List<T> collectEntities(List<T>... entities) {
        return Stream.of(entities)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntities(List<T> entities, T entity) {
        return Stream.concat(Stream.of(entity), entities.stream())
                .collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesExceptOne(List<T> entities, T entity) {
        return entities.stream()
                .filter(t -> t!= entity)
                .collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesSortedExceptOne(List<T> entities, T entity, Comparator<T> comparator) {
        return entities.stream()
                .filter(t -> t!= entity)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @SafeVarargs
    static <T extends AbstractBaseEntity> List<T> collectEntitiesSorted(Comparator<T> comparator, List<T>... entities) {
        return Stream.of(entities)
                .flatMap(Collection::stream)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesSorted(Comparator<T> comparator, List<T> entities, T entity) {
        return Stream.concat(Stream.of(entity), entities.stream())
                .sorted(comparator)
                .collect(Collectors.toList());
    }


/*
    @SafeVarargs
    static <T extends AbstractBaseEntity> List<T> collectEntitiesAddingToBeginning(List<T>... entities) {
        return Stream.of(entities)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesAddingToBeginning(List<T> entities, T entity) {
        return Stream.concat(Stream.of(entity), entities.stream())
                .collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesAddingToEnd(List<T> entities, T entity) {
        return Stream.concat(entities.stream(), Stream.of(entity))
                .collect(Collectors.toList());
    }
*/
    static <T extends AbstractBaseEntity> List<T> getEntitiesSorted(List<T> entities, Comparator<T> comparator) {
        return entities.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }


    static <T extends AbstractBaseEntity> List<T> collectEntitiesByCriteria(List<T> entities, Predicate<T> predicate) {
        return entities.stream().filter(predicate).collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesByTwoCriteria(List<T> entities, Predicate<T> predicate1, Predicate<T> predicate2) {
        return entities.stream().filter(predicate1.and(predicate2)).collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesByCriteriaSorted(List<T> entities, Predicate<T> predicate, Comparator<T> comparator) {
        return entities.stream().filter(predicate).sorted(comparator).collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesByTwoCriteriaSorted(List<T> entities, Predicate<T> predicate1, Predicate<T> predicate2, Comparator<T> comparator) {
        return entities.stream().filter(predicate1.and(predicate2)).sorted(comparator).collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesByCriteriaSortedByIdDesc(List<T> entities, Predicate<T> predicate) {
        return entities.stream().filter(predicate).sorted(SORT_BY_ID_DESC).collect(Collectors.toList());
    }

    static <T extends AbstractBaseEntity> List<T> collectEntitiesByTwoCriteriaSortedByIdDesc(List<T> entities, Predicate<T> predicate1, Predicate<T> predicate2) {
        return entities.stream().filter(predicate1.and(predicate2)).sorted(SORT_BY_ID_DESC).collect(Collectors.toList());
    }

}
