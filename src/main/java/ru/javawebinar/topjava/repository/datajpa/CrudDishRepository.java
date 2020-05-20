package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Dish;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Dish save(Dish item);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.added BETWEEN :startDate AND :endDate ORDER BY d.added DESC")
    List<Dish> getAllBetween(@Param("restaurantId") int restaurantId, @Param("startDate") LocalDateTime start, @Param("endDate") LocalDateTime end);

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id = ?1 and d.restaurant.id = ?2")
    Dish getWithRestaurant(int id);

//List<Dish> getDishesByRestaurant_IdAndAddedBetween(int restaurantId, LocalDateTime start, LocalDateTime end);
//    @Query("SELECT d FROM  Dish d WHERE d.restaurant.id=:restaurantId AND d.added =:today")
//    List<Dish> getDishesByRestaurantForToday(@Param("today") LocalDate today, @Param("restaurantId") int restaurantId);
//    List<Dish> getDishesByRestaurant_IdAndAdded(int restaurantId, LocalDate date);
//  List<Dish> getDishesByAddedBetweenAndRestaurant_Id(@NotNull LocalDateTime added, @NotNull LocalDateTime added2, int id);
//    @Query("SELECT d FROM  Dish d WHERE d.restaurant.id=:restaurantId AND d.added =:today")
//    List<Dish> getDishesByRestaurantForToday(@Param("today") LocalDate today, @Param("restaurantId") int restaurantId);
//    @Query("SELECT d FROM Dish d WHERE d.restaurant.id =:restaurantId")
//    List<Dish> getDishesByRestaurantId(@Param("restaurantId") int restaurantId);
}
