package ru.javawebinar.topjava.model.entitiesWithHistory;

import ru.javawebinar.topjava.model.baseEntities.Restaurant;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;


// Class Not Used
public class UserWithHistory {

    private String email;
    private String password;
    private boolean votedToday;
    private Role userRole;
    private boolean enabled;
    private LocalDateTime todaysVotingTime;
    private Restaurant todaysVote;
    private Map<LocalDate, Restaurant> voteHistory;
}
