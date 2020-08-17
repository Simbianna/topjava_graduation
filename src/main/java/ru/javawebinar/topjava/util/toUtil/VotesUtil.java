package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

public class VotesUtil {

    public static VoteTo asToFotUser(Vote vote) {
        return new VoteTo(vote.getId(), vote.getVotingDate(), RestaurantsUtil.asToForUserNoDishes(vote.getRestaurant()));
    }

    public static List<VoteTo> asToListForUser(List<Vote> votes) {
        return votes.stream()
                .map(VotesUtil::asToFotUser)
                .collect(Collectors.toList());
    }

}
