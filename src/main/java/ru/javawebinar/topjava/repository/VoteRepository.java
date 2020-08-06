package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    // null if not found
    Vote getById(int id);

    // null if vote does not belong to userId
    Vote getByIdForUser(int id, int userId);

    // null if not found
    Vote getLastVoteForUserBetweenDateTimes(int userId, LocalDateTime startDate, LocalDateTime endDate);

    // null if not found. Only for update.
    Vote saveForAdmin(Vote vote);

    // null if updated vote do not belong to userId
    Vote saveForUser(Vote vote, int userId);

    // false if not found
    boolean delete(int id);

    // false if vote do not belong to userId
    boolean deleteForUser(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll();

    // ORDERED dateTime desc
    List<Vote> getAllForUser(int userId);

    // ORDERED dateTime desc
    List<Vote> getAllForUserBetween(int userId, LocalDateTime start, LocalDateTime end);

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



}
