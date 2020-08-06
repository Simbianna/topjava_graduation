package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getDaysBeginning;
import static ru.javawebinar.topjava.util.DateTimeUtil.getDaysEnd;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    VoteService(VoteRepository repository) {
        this.voteRepository = repository;
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(voteRepository.getByIdForUser(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.deleteForUser(id, userId), id);
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    //TODO подумать насчет userId
    public List<Vote> getAllForRestaurant(int restaurantId, int userId) {
        return voteRepository.getAllForRestaurant(restaurantId);
    }

    public List<Vote> getAllForRestaurantForOneDay(int restaurantId, LocalDateTime dateTime) {
        Assert.notNull(dateTime, "dateTime must not be null");
        return getAllForRestaurantBetweenDateTimes(restaurantId, getDaysBeginning(dateTime), getDaysEnd(dateTime));
//        return repository.getAllForRestaurant(restaurantId, getDaysBeginning(dateTime), getDaysEnd(dateTime));
    }

    public List<Vote> getAllForRestaurantForOneDay(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return getAllForRestaurantBetweenDateTimes(restaurantId, getDaysBeginning(date), getDaysEnd(date));
//        return repository.getAllForRestaurant(restaurantId, getDaysBeginning(dateTime), getDaysEnd(dateTime));
    }

    public List<Vote> getAllForRestaurantBetweenDateTimes(int restaurantId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return voteRepository.getAllForRestaurantBetween(restaurantId, startDateTime, endDateTime);
    }

    public Vote getLastForUser(int userId) {
        return checkNotFoundWithId(voteRepository.getLastVoteForUserBetweenDateTimes(userId), userId);
    }

    public List<Vote> getAllForUser(int userId) {
        return voteRepository.getAllForUser(userId);
    }

    public List<Vote> getAllForUserBetweenDateTimes(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return voteRepository.getAllForUserBetween(userId, startDateTime, endDateTime);
    }

    public void update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(voteRepository.saveForUser(vote, userId), vote.getId());
    }

    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return voteRepository.saveForUser(vote, userId);
    }

    public Vote getWithUser(int id, int userId) {
        return checkNotFoundWithId(voteRepository.getWithUser(id, userId), id);
    }

    public Vote getWithRestaurant(int id, int userId) {
        return checkNotFoundWithId(voteRepository.getWithRestaurant(id, userId), id);
    }


}
