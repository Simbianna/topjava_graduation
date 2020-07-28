package ru.javawebinar.topjava.repository.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Menu;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Menu save(Menu item);

    @Query("SELECT distinct m FROM Menu m left join fetch m.dishes d WHERE m.id=:id")
    Menu getWithDishes(@Param("id") int id);

    @Query("SELECT distinct m FROM Menu m left join fetch m.dishes left join fetch m.restaurant d WHERE m.id=:id")
    Menu getWithDishesAndMenu(@Param("id") int id);

/*    @Query("SELECT distinct m FROM Menu m left join fetch m.dishes d")
    List<Menu> getAllWithDishes();*/

}
