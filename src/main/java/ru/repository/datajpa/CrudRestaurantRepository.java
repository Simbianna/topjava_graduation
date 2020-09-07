package ru.repository.datajpa;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT distinct r FROM Restaurant r left join fetch r.dishes d WHERE d.included = true")
    List<Restaurant> getAllWithActualMenu();

    @Query("SELECT distinct r FROM Restaurant r left join fetch r.dishes d WHERE (d.restaurant.id=:id and d.included = true)")
    Restaurant getWithActualMenu(@Param("id") int id);

}
