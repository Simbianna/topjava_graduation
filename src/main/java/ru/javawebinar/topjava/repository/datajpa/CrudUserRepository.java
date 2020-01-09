package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.baseEntities.User;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer>{
}
