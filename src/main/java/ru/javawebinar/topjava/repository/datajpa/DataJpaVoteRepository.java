package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    // private static final Sort SORT_DATE_USER = new Sort(Sort.Direction.ASC, "votingDateTime", "user");
    // private static final Sort SORT_BY_DATETIME = new Sort(Sort.Direction.ASC, "votingDateTime");
    private static final Sort SORT_BY_ID = Sort.by(Sort.Direction.DESC, "id");

    @Autowired
    CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    public boolean delete(int id, int userId) {
        return voteRepository.delete(id, userId) != 0;
    }

    public Vote get(int id, int userId) {
        //  return voteRepository.getVoteByIdAndUserId(id, userId);
        return voteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    public List<Vote> getAllForRestaurant(int restaurantId) {
        return voteRepository.getAllByRestaurant(restaurantId);
    }

    public List<Vote> getAllForRestaurantBetween(int restaurantId, LocalDateTime start, LocalDateTime end) {
        return voteRepository.getAllByRestaurantBetween(restaurantId, start, end);
    }

    public Vote getLastForUser(int userId) {
        return voteRepository.findFirstByUser_IdOrderByVotingDateTimeDesc(userId);
    }

    public List<Vote> getAllForUser(int userId) {
        return voteRepository.getAllForUser(userId);
    }

    public List<Vote> getAllForUserBetween(int userId, LocalDateTime start, LocalDateTime end) {
        return voteRepository.getAllForUserBetween(userId, start, end);
    }

    public Vote getWithUser(int id, int userId) {
        return voteRepository.getWithUser(id, userId);
    }

    public Vote getWithRestaurant(int id, int restaurantId) {
        return voteRepository.getWithRestaurant(id, restaurantId);
    }




    public List<Vote> getAll() {
        return voteRepository.findAll(SORT_BY_ID);
    }

//   public Vote getById(int id){return  voteRepository.getOne(id);}

//    public List<Vote> getAllForUser(int userId) {
//        return voteRepository.findAllByUser_IdOrOrderByVotingDateTimeDesc(userId);
//    }
}
