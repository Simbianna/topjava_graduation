package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Vote;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DataJpaVoteRepository {

    @Autowired
    CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        if (vote.isNew() && get(vote.getId(), userId) != null) {
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    public boolean delete(int id, int userId) {
        return voteRepository.delete(id, userId) != 0;
    }

    public Vote get(int id, int userId) {
        return voteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    public List<Vote> getAll(int userId) {
      return   voteRepository.getAll(userId);
    }

    public Vote getWithUser(int id, int userId) {
        return voteRepository.getWithUser(id, userId);
    }


}
