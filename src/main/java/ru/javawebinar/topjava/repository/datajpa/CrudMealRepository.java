package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Restaurant;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Meal getWithRestaurant(int id);

    List<Meal> getMealsByRestaurant_Id(int restaurantId);

    List<Meal> getMealsByAddedBetweenAndRestaurant_Id(@NotNull LocalDateTime added, @NotNull LocalDateTime added2, int id);

    @Query("SELECT m FROM  Meal m WHERE m.restaurant.id=:restaurantId AND m.added =:today")
    List<Meal> getMealsByRestaurantForToday(@Param("today") LocalDate today, @Param("restaurantId") int restaurantId);

}
