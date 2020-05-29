package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    // null if updated vote do not belong to userId
    Vote save(Vote vote, int userId);

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    // null if vote do not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAllForUser(int userId);

    // ORDERED dateTime desc
    List<Vote> getAllForUserBetween(int userId, LocalDateTime start, LocalDateTime end);

    // ORDERED dateTime desc
    List<Vote> getAllForRestaurant(int restaurantId);

    // ORDERED dateTime desc
    List<Vote> getAllForRestaurantBetween(int restaurantId, LocalDateTime start, LocalDateTime end);

    Vote getLastForUser(int userId);

    default Vote getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    default Vote getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }

        // ORDERED dateTime desc
    List<Vote> getAll();

}
