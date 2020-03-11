package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.datajpa.DataJpaVoteRepository;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final DataJpaVoteRepository repository;

    @Autowired
    VoteService(DataJpaVoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    public Vote getLastForUser(int userId){
        return repository.getLastForUser(userId);
    }

    public Vote update(Vote vote, int userID){return repository.save(vote, userID);}

}
