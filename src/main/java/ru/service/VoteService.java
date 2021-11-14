package ru.service;

import ru.dto.VoteTo;
import ru.model.Vote;

import java.util.List;

public interface VoteService {
    List<Vote> getAllWithRestaurant();

    Vote getById(int id);

    Vote voteForRestaurant(int restaurantId, int userId);

    List<VoteTo> getAllForUserWithRestaurant(int userId);

    VoteTo getByIdForUserWithRestaurant(int id, int userId);

    void deleteByIdForUser(int id, int userId);

    Vote createForUser(Vote vote, int userId);

    Vote updateForUser(Vote vote, int voteId, int userId);
}
