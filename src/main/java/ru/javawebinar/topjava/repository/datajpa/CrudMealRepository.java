package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:retaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Meal save(Meal item);

    //admin command
    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:retaurantId ORDER BY m.added DESC")
    List<Meal> getAll(@Param("mealId") int mealId);

    //admin command
    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Meal m WHERE m.restaurant.id=:restaurantId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("mealId") int mealId);

    //TODO
    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Meal m WHERE m.restaurant.id=:restaurantId AND m.dateTime = :today ORDER BY m.dateTime DESC")
    List<Meal> getForToday(@Param("today") LocalDate today, @Param("mealId") int mealId);


    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Meal getWithRestaurant(int id, int mealId);

}
