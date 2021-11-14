package ru.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dto.VoteTo;
import ru.model.Restaurant;
import ru.model.Vote;
import ru.repository.RestaurantRepository;
import ru.repository.UserRepository;
import ru.repository.VoteRepository;
import ru.util.ValidationUtil;
import ru.util.exception.IllegalRequestDataException;
import ru.util.toUtil.VoteToDtoTransformer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final ValidationUtil validator;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final VoteToDtoTransformer voteTransformer;

    @Override
    public List<Vote> getAllWithRestaurant() {
        return null;
    }

    @Override
    public Vote getById(int id) {
        return validator.checkNotFoundWithId(voteRepository.getByIdWithRestaurant(id), id);
    }

    @Override
    public Vote voteForRestaurant(int restaurantId, int userId) {
        Restaurant restaurant = validator.checkNotFoundWithId(restaurantRepository.findById(restaurantId).orElseGet(null), restaurantId);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Vote todaysVote = voteRepository.getByUserIdAndVotingDateWithRestaurant(userId, currentDateTime.toLocalDate());
        Vote persistentVote;
        if (todaysVote != null) {
            validator.checkVoteCanBeUpdatedToday(currentDateTime);
            todaysVote.setVotingDate(currentDateTime.toLocalDate());
            todaysVote.setRestaurant(restaurant);
            persistentVote = voteRepository.save(todaysVote);
        } else {
            persistentVote = voteRepository.save(new Vote(LocalDate.now(), restaurant, userRepository.getOne(userId)));
        }
        return persistentVote;
    }

    @Override
    public List<VoteTo> getAllForUserWithRestaurant(int userId) {
        return voteTransformer.asToListForUser(voteRepository.getAllForUserWithRestaurant(userId));
    }

    @Override
    public VoteTo getByIdForUserWithRestaurant(int id, int userId) {
        return voteTransformer.asToFotUser(validator.checkNotFoundWithId(voteRepository.getByIdForUserWithRestaurant(id, userId), id));
    }

    @Override
    public void deleteByIdForUser(int id, int userId) {
        validator.checkNotFoundWithId(voteRepository.deleteByIdForUser(id, userId), id);

    }

    @Override
    public Vote createForUser(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        LocalDate currentDate = LocalDate.now();
        if (voteRepository.getByUserIdAndVotingDate(userId, currentDate) != null) {
            throw new IllegalRequestDataException("can`t create new vote today");
        }
        validator.checkNew(vote);
        vote.setVotingDate(currentDate);
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    @Override
    public Vote updateForUser(Vote vote, int voteId, int userId) {
        Assert.notNull(vote, "vote must not be null");
        LocalDateTime currentDateTime = LocalDateTime.now();
        validator.checkVoteCanBeUpdatedToday(currentDateTime);
        validator.assureIdConsistent(vote, voteId);
        vote.setVotingDate(LocalDate.now());
        vote.setUser(userRepository.getOne(userId));
        return validator.checkNotFoundWithId(voteRepository.save(vote), voteId);
    }
}
