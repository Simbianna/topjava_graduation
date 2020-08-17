package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r WHERE v.user.id=:userId ORDER BY v.votingDate DESC")
    List<Vote> getAllForUserWithRestaurant(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r WHERE v.id=:id and v.user.id=:userId  ORDER BY v.votingDate DESC")
    Vote getByIdForUserWithRestaurant(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId and v.votingDate=:date ")
    Vote getByUserIdAndVotingDate(int userId, LocalDate date);

    @Query("SELECT v FROM Vote v left join fetch v.restaurant r WHERE v.user.id=:userId and v.votingDate=:date ")
    Vote getByUserIdAndVotingDateWithRestaurant(@Param("userId") int userId, @Param("date") LocalDate date);



    /*
     //Vote findFirstByUser_IdOrderByVotingDateTimeDesc(int userId);
    //@Query(value = "SELECT v FROM Vote v WHERE v.user.id=:userId and v.votingDateTime BETWEEN :startDate AND :endDate")
    @Query(value = "SELECT 1 FROM VOTES WHERE USER_ID=:userId and ADDED between :startDate and:endDate ORDER BY ADDED DESC ", nativeQuery = true)
    Vote getLastVoteForUserBetweenDateTimes(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.votingDateTime BETWEEN :startDate AND :endDate ORDER BY v.votingDateTime DESC")
    List<Vote> getAllForUserBetween(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId ORDER BY v.votingDateTime DESC")
    List<Vote> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId and v.votingDateTime BETWEEN :startDate AND :endDate ORDER BY v.votingDateTime DESC")
    List<Vote> getAllByRestaurantBetween(@Param("restaurantId") int restaurantId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user WHERE v.id = ?1 and v.user.id = ?2")
    Vote getWithUser(int id, int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user WHERE v.id = ?1 and v.restaurant.id = ?2")
    Vote getWithRestaurant(int id, int restaurantId);
     */

    /*
   List<Vote> findAllByUser_IdOrderByVotingDateTimeDesc(int userId);
   List<Vote> findAllByRestaurant_IdAndVotingDateTimeBetweenOrderByIdDesc(int restaurantId, LocalDateTime start, LocalDateTime end);
   List<Vote> findAllByRestaurant_IdOrderByVotingDateTimeDesc(int restaurantId);
   List<Vote> findAllByUser_IdAndVotingDateTimeBetweenOrderByVotingDateTimeDesc(int userId, LocalDateTime start, LocalDateTime end);
    */
}
