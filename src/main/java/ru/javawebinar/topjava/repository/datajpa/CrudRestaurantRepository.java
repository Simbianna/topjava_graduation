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

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithDishes(int id);

    @Query("select new ru.javawebinar.topjava.model.Restaurant(r.id, r.name, count(v)) from Restaurant r left join Vote v on r.id = v.restaurant.id where r.id = :id and v.restaurant.id = :id group by  r.id , r.name")
    Restaurant getWithRating(@Param("id") int id);

    // @Query("select r, count(v) from Restaurant r left join Vote v on r.id = v.restaurant.id group by r")
    // @Query("select res from Restaurant res left join (Select v.restaurant.id rty, count(v) cnt FROM Vote v group by v.restaurant.id) m on res.id = m.rty")
   // @Query("SELECT r.id, r.name, r. FROM Restaurant r LEFT JOIN FETCH r.votes")
    @Query("select new ru.javawebinar.topjava.model.Restaurant(r.id, r.name, count(v)) from Restaurant r left join Vote v on r.id = v.restaurant.id group by r.id, r.name")
    List<Restaurant> getAllWithRating();


    //  Restaurant getWithDishesAndRating(int id);

    /*@EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    void getWithVotes(int id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    void getWithDishesAndVotes(int id);*/
}
