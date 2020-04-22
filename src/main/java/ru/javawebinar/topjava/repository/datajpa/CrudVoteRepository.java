package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDateTime;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Vote save(Vote vote);

   // Vote getVoteByIdAndUserId(int restaurantId, int userId);

    Vote findFirstByUser_IdOrderByVotingDateTimeDesc(int userId);

    List<Vote> findAllByUser_IdOrderByVotingDateTimeDesc(int userId);

    List<Vote> findAllByUser_IdAndVotingDateTimeBetweenOrderByVotingDateTimeDesc(int userId, LocalDateTime start, LocalDateTime end);

    List<Vote> findAllByRestaurant_IdOrderByVotingDateTimeDesc(int restaurantId);

    List<Vote> findAllByRestaurant_IdAndVotingDateTimeBetweenOrderByIdDesc(int restaurantId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user WHERE v.id = ?1 and v.user.id = ?2")
    Vote getWithUser(int id, int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user WHERE v.id = ?1 and v.restaurant.id = ?2")
    Vote getWithRestaurant(int id, int restaurantId);

    //    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.votingDateTime DESC")
//    List<Vote> getAllForUser(@Param("userId") int userId);

    /* @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.votingDateTime DESC")
    List<Vote> getAllForUser(@Param("userId") int userId);*/
}
