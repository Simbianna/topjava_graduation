package ru.javawebinar.topjava.testData;

import ru.javawebinar.topjava.model.Vote;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.testData.DataCollectorUtil.*;
import static ru.javawebinar.topjava.testData.RestaurantTestData.*;
import static ru.javawebinar.topjava.testData.UserTestData.*;

public class VoteTestData {
    private static final Comparator<Vote> SORT_VOTES_BY_DATETIME_DESC = (o1, o2) -> o2.getVotingDate().compareTo(o1.getVotingDate());
    public static int VOTE_ID = START_SEQ + 17;

   /* public static final Vote USER_VOTE_D1 = new Vote(VOTE_ID, of(2020, Month.MARCH, 30, 9, 0), STEAK_HOUSE, USER);
    public static final Vote ADMIN_VOTE_D1 = new Vote(VOTE_ID + 1, of(2020, Month.MARCH, 30, 10, 0), ITALIAN, ADMIN);
    public static final Vote USER_VOTE_D2 = new Vote(VOTE_ID + 2, of(2020, Month.MARCH, 31, 9, 0), ITALIAN, USER);
    public static final Vote ADMIN_VOTE_D2 = new Vote(VOTE_ID + 3, of(2020, Month.MARCH, 31, 10, 0), ITALIAN, ADMIN);
    private static final Vote DELETED_VOTE = USER_VOTE_D1;
    public static final Vote ITALIAN_VOTE_UPDATED = new Vote(VOTE_ID, of(2020, Month.MARCH, 30, 10, 0), ITALIAN, USER);
    public static final Vote ITALIAN_VOTE_CREATED = new Vote(null, of(2020, Month.APRIL, 1, 9, 0), ITALIAN, USER);
*/
    public static final LocalDateTime CHECK_DATE_TIME = of(2020, Month.MARCH, 31, 10, 0);
    private static final LocalDateTime CHECK_DATE_START = of(CHECK_DATE_TIME.toLocalDate(), LocalTime.MIN);
    private static final LocalDateTime CHECK_DATE_END = of(CHECK_DATE_TIME.toLocalDate(), LocalTime.MAX);
    public static final LocalDateTime START_DATE_TIME = of(2020, Month.MARCH, 30, 9, 0);
    public static final LocalDateTime END_DATE_TIME = of(2020, Month.MARCH, 31, 11, 0);

/*
//    public static final List<Vote> ALL_VOTES = List.of(ADMIN_VOTE_D2, USER_VOTE_D2, ADMIN_VOTE_D1, USER_VOTE_D1);
    public static final List<Vote> ALL_VOTES_WITHOUT_DELETED = collectEntitiesByCriteriaSortedByIdDesc(ALL_VOTES, vote -> vote != DELETED_VOTE);
    public static final List<Vote> ALL_VOTES_BY_USER1 = collectEntitiesByCriteriaSortedByIdDesc(ALL_VOTES, vote -> vote.getUser().getId() == USER_ID);
    public static final List<Vote> ALL_VOTES_FOR_ITALIAN_SORTED = collectEntitiesByCriteriaSorted(ALL_VOTES, vote -> vote.getRestaurant().getId() == ITALIAN_ID, SORT_VOTES_BY_DATETIME_DESC);
    public static final List<Vote> ALL_VOTES_FOR_ITALIAN_CHECK_DATE = getAllVotesBetweenDateTimes(ALL_VOTES_FOR_ITALIAN_SORTED, CHECK_DATE_START, CHECK_DATE_END);
    public static final List<Vote> ALL_VOTES_FOR_ITALIAN_BETWEEN_DATE_TIMES = getAllVotesBetweenDateTimes(ALL_VOTES_FOR_ITALIAN_SORTED, START_DATE_TIME, END_DATE_TIME);
   public static final List<Vote> ALL_VOTES_FOR_ITALIAN_WITH_CREATED = collectEntitiesSorted(SORT_VOTES_BY_DATETIME_DESC, ALL_VOTES_FOR_ITALIAN_SORTED, ITALIAN_VOTE_CREATED);
    //   public static final List<Vote> ALL_VOTES_BETWEEN_DATE_TIMES = getAllVotesBetweenDateTimes(ALL_VOTES, START_DATE_TIME, END_DATE_TIME);
*/

  /*  private static List<Vote> getAllVotesBetweenDateTimes(List<Vote> votes, LocalDateTime start, LocalDateTime end) {
        return votes.stream().filter(vote -> vote.getVotingDate().equals(start) || vote.getVotingDate().equals(end)
                || vote.getVotingDate().isAfter(start) && vote.getVotingDate().isBefore(end)).collect(Collectors.toList());
    }
*/
    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "restaurant");
    }

    public static void assertMatch(List<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(List<Vote> actual, List<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "restaurant").isEqualTo(expected);
    }

}
