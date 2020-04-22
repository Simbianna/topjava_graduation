package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.util.Util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryVoteRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryVoteRepository.class);

    private Map<Integer, InMemoryBaseRepository<Vote>> usersVotesMap = new ConcurrentHashMap<>();

    public Vote save(Vote vote, int userId) {
            Objects.requireNonNull(vote, "Vote must not be null");
            InMemoryBaseRepository<Vote> votes = usersVotesMap.computeIfAbsent(userId, new Function<Integer, InMemoryBaseRepository<ru.javawebinar.topjava.model.Vote>>() {
                @Override
                public InMemoryBaseRepository<ru.javawebinar.topjava.model.Vote> apply(Integer uid) {
                    return new InMemoryBaseRepository<>();
                }
            });
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


    public List<Vote> getAll(int userId) {
        return getAllFiltered(userId, vote -> true);
    }

    public Vote getLastForUser(int userId){
        InMemoryBaseRepository<Vote> votes = usersVotesMap.get(userId);
        return votes.getCollection().stream().max(Comparator.comparing(Vote::getVotingDateTime)).orElse(null);
    }


    public List<Vote> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime, "startDateTime must not be null");
        Objects.requireNonNull(endDateTime, "endDateTime must not be null");
        return getAllFiltered(userId, vote -> Util.isBetween(vote.getVotingDateTime(), startDateTime, endDateTime));
    }

    private List<Vote> getAllFiltered(int userId, Predicate<Vote> filter) {
        InMemoryBaseRepository<Vote> votes = usersVotesMap.get(userId);
        return votes == null ? Collections.emptyList() :
                votes.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Vote::getVotingDateTime).reversed())
                        .collect(Collectors.toList());
    }



}
