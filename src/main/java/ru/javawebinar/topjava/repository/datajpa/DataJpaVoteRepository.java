package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private static final Sort SORT_BY_ID = Sort.by(Sort.Direction.DESC, "id");

    @Autowired
    CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Override
    public Vote getById(int id) {
        return voteRepository.findById(id).orElse(null);
    }

    @Override
    public Vote getByIdForUser(int id, int userId) {
        return voteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Vote getLastVoteForUserBetweenDateTimes(int userId, LocalDateTime startDate, LocalDateTime endDate) {
        return voteRepository.getLastVoteForUserBetweenDateTimes(userId, startDate, endDate);
    }


     /* @Override
    public Vote getLastForUser(int userId) {
        return voteRepository.findFirstByUser_IdOrderByVotingDateTimeDesc(userId);
    }*/

   /* @Override
    public Vote getLastForUser(int userId) {
        return voteRepository.findFirstByUser_IdOrderByVotingDateTimeDesc(userId);
    }*/

    @Override
    @Transactional
    public Vote saveForAdmin(Vote vote) {
        if (!vote.isNew()) {
            return null;
        }
        return voteRepository.save(vote);
    }

    @Override
    @Transactional
    public Vote saveForUser(Vote vote, int userId) {
        if (!vote.isNew() && getByIdForUser(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return voteRepository.delete(id) != 0;
    }

    @Override
    public boolean deleteForUser(int id, int userId) {
        return voteRepository.delete(id, userId) != 0;
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll(SORT_BY_ID);
    }

    @Override
    public List<Vote> getAllForUser(int userId) {
        return voteRepository.getAllForUser(userId);
    }

    @Override
    public List<Vote> getAllForUserBetween(int userId, LocalDateTime start, LocalDateTime end) {
        return voteRepository.getAllForUserBetween(userId, start, end);
    }

    @Override
    public Vote getWithUser(int id, int userId) {
        return voteRepository.getWithUser(id, userId);
    }

    @Override
    public Vote getWithRestaurant(int id, int restaurantId) {
        return voteRepository.getWithRestaurant(id, restaurantId);
    }

    @Override
    public List<Vote> getAllForRestaurant(int restaurantId) {
        return voteRepository.getAllByRestaurant(restaurantId);
    }

    @Override
    public List<Vote> getAllForRestaurantBetween(int restaurantId, LocalDateTime start, LocalDateTime end) {
        return voteRepository.getAllByRestaurantBetween(restaurantId, start, end);
    }


}
