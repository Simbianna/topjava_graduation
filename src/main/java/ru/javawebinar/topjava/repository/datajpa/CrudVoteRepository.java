package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r ORDER BY v.votingDate DESC")
    List<Vote> getAllWithRestaurant();

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r WHERE v.user.id=:userId ORDER BY v.votingDate DESC")
    List<Vote> getAllForUserWithRestaurant(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r WHERE v.id=:id")
    Vote getByIdWithRestaurant(@Param("id") int id);

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r WHERE v.id=:id and v.user.id=:userId")
    Vote getByIdForUserWithRestaurant(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId and v.votingDate=:date ")
    Vote getByUserIdAndVotingDate(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r WHERE v.user.id=:userId and v.votingDate=:date ")
    Vote getByUserIdAndVotingDateWithRestaurant(@Param("userId") int userId, @Param("date") LocalDate date);

}
