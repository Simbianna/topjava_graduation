package ru.util.toUtil;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.model.Vote;
import ru.dto.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class VoteToDtoTransformer {
    private final RestaurantToDtoTransformer restaurantTransformer;

    public VoteTo asToFotUser(Vote vote) {
        return new VoteTo(vote.getId(), vote.getVotingDate(), restaurantTransformer.asToForUserNoDishes(vote.getRestaurant()));
    }

    public List<VoteTo> asToListForUser(List<Vote> votes) {
        return votes.stream()
                .map(this::asToFotUser)
                .collect(Collectors.toList());
    }

}
