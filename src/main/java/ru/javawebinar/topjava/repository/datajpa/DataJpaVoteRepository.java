package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.repository.VoteRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    @Autowired
    CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Override
    public List<Vote> getAllWithRestaurant() {
        return voteRepository.getAllWithRestaurant();
    }

    @Override
    public List<Vote> getAllForUserWithRestaurant(int userId) {
        return voteRepository.getAllForUserWithRestaurant(userId);
    }

    @Override
    public Vote getByIdWithRestaurant(int id) {
        return voteRepository.getByIdWithRestaurant(id);
    }

    @Override
    public Vote getByIdForUser(int id, int userId) {
        return voteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Vote getByIdForUserWithRestaurant(int id, int userId) {
        return voteRepository.getByIdForUserWithRestaurant(id, userId);
    }

    @Override
    public Vote getForUserByDate(int userId, LocalDate date) {
        return voteRepository.getByUserIdAndVotingDate(userId, date);
    }

    @Override
    public Vote getForUserByDateWithRestaurant(int userId, LocalDate date) {
        return voteRepository.getByUserIdAndVotingDateWithRestaurant(userId, date);
    }

    @Override
    public boolean deleteById(int id) {
        return voteRepository.delete(id) != 0;
    }

    @Override
    public boolean deleteByIdForUser(int id, int userId) {
        return voteRepository.delete(id, userId) != 0;
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
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



    /* @Override
    public Vote getLastForUser(int userId) {
        return voteRepository.findFirstByUser_IdOrderByVotingDateTimeDesc(userId);

    @Override
    public Vote getLastVoteForUserBetweenDateTimes(int userId, LocalDateTime startDate, LocalDateTime endDate) {
        return voteRepository.getLastVoteForUserBetweenDateTimes(userId, startDate, endDate);
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
    public List<Vote> getAllForUserBetween(int userId, LocalDateTime start, LocalDateTime end) {
        return voteRepository.getAllForUserBetween(userId, start, end);
    }

    @Override
    public List<Vote> getAllForRestaurant(int restaurantId) {
        return voteRepository.getAllByRestaurant(restaurantId);
    }

    @Override
    public List<Vote> getAllForRestaurantBetween(int restaurantId, LocalDateTime start, LocalDateTime end) {
        return voteRepository.getAllByRestaurantBetween(restaurantId, start, end);
    }
 }*/
}
