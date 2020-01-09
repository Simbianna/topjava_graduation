package ru.javawebinar.topjava.repository.datajpa;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.baseEntities.Restaurant;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
