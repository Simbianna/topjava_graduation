package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;
import ru.javawebinar.topjava.util.Util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryVoteRepository extends InMemoryBaseRepository<Vote> implements VoteRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryVoteRepository.class);

    private Map<Integer, InMemoryBaseRepository<Vote>> usersVotesMap = new ConcurrentHashMap<>();

    public Vote save(Vote vote, int userId) {
            Objects.requireNonNull(vote, "Vote must not be null");
            InMemoryBaseRepository<Vote> votes = usersVotesMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepository<>());
            return votes.save(vote);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }


    public boolean delete(int id, int userId) {
        InMemoryBaseRepository<Vote> votes = usersVotesMap.get(userId);
        return votes != null && votes.delete(id);
    }

    public Vote get(int id, int userId) {
        InMemoryBaseRepository<Vote> votes = usersVotesMap.get(userId);
        return votes == null ? null : votes.get(id);
    }


    public Vote getLastForUser(int userId){
        InMemoryBaseRepository<Vote> votes = usersVotesMap.get(userId);
        return votes.getCollection().stream().max(Comparator.comparing(Vote::getVotingDateTime)).orElse(null);
    }

    public List<Vote> getAllForRestaurant(int restaurantId){
        return getAllFiltered(restaurantId, vote -> true);
    }

    @Override
    public List<Vote> getAllForRestaurantBetween(int restaurantId, LocalDateTime start, LocalDateTime end) {
        return getBetween(start, end, restaurantId);
    }

    public List<Vote> getAllForUser(int userId){
        return getAllFiltered(userId, vote -> true);
    }

    @Override
    public List<Vote> getAllForUserBetween(int userId, LocalDateTime start, LocalDateTime end) {
        return getBetween(start, end, userId);
    }


    public List<Vote> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int id) {
        Objects.requireNonNull(startDateTime, "startDateTime must not be null");
        Objects.requireNonNull(endDateTime, "endDateTime must not be null");
        return getAllFiltered(id, vote -> Util.isBetween(vote.getVotingDateTime(), startDateTime, endDateTime));
    }

    private List<Vote> getAllFiltered(int restaurantOrUserId, Predicate<Vote> filter) {
        InMemoryBaseRepository<Vote> votes = usersVotesMap.get(restaurantOrUserId);
        return votes == null ? Collections.emptyList() :
                votes.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Vote::getVotingDateTime).reversed())
                        .collect(Collectors.toList());
    }

    @Override
    public Vote getWithUser(int id, int userId) {
        return null;
    }


    @Override
    public Vote getWithRestaurant(int id, int restaurantId) {
        return null;
    }


    public List<Vote> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(Vote::getVotingDateTime))
                .collect(Collectors.toList());
    }

}
