package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    // ordered dateTime desc
    List<Vote> getAllWithRestaurant();

    // ordered dateTime desc
    List<Vote> getAllForUserWithRestaurant(int userId);

    // null if not found
    Vote getByIdWithRestaurant(int id);

    // null if not found
    Vote getByIdForUser(int id, int userId);

    // null if vote does not belong to userId
    Vote getByIdForUserWithRestaurant(int id, int userId);

    // null if vote does not belong to userId
    Vote getForUserByDateWithRestaurant(int userId, LocalDate date);

    // false if not found
    boolean deleteById(int id);

    // false if vote do not belong to userId
    boolean deleteByIdForUser(int id, int userId);

    // null if not found.
    Vote save(Vote vote);

    // null if updated vote do not belong to userId
    Vote saveForUser(Vote vote, int userId);

    // null if not found
    Vote getForUserByDate(int userId, LocalDate date);


    /* ORDERED dateTime desc
    List<Vote> getAllForUserBetween(int userId, LocalDateTime start, LocalDateTime end);

    // null if not found
    Vote getLastVoteForUserBetweenDateTimes(int userId, LocalDateTime startDate, LocalDateTime endDate);

    // ORDERED dateTime desc
    List<Vote> getAllForRestaurant(int restaurantId);

    // ORDERED dateTime desc
    List<Vote> getAllForRestaurantBetween(int restaurantId, LocalDateTime start, LocalDateTime end);

    default Vote getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    default Vote getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
    */
}
