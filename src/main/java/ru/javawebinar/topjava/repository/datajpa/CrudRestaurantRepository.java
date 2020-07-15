package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant item);

    @Query("SELECT distinct r FROM Restaurant r left join fetch r.menu m left join fetch m.dishes WHERE r.menu.restaurant.id=:id ")
    Restaurant getWithMenu(@Param("id") int id);

   @Query("SELECT distinct r FROM Restaurant r left join fetch r.menu m left join fetch m.dishes")
   List<Restaurant> getAllWithDishes();



}
