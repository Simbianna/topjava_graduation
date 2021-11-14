package ru.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.model.Dish;

import java.util.List;

@Repository
//@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    /*@Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);*/

    void deleteByIdAndRestaurantId(int dishId, int restaurantId);

    Dish findByIdAndRestaurantId(int dishId, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id =:restaurantId ORDER BY d.added DESC")
    List<Dish> findAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Dish save(Dish item);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id =:restaurantId AND d.included=true ORDER BY d.added DESC")
    List<Dish> findAllIncludedByRestaurantId(@Param("restaurantId") int restaurantId);

    //Dish saveForRestaurant(Dish dish, int restaurantId);
}
