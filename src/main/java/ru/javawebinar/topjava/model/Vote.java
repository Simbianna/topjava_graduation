package ru.javawebinar.topjava.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity{

    @Column(name = "added", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime votingDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //Надо подумать, удалять ли голос при удалении рестика
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private User user;

    public Vote() {
    }

    public Vote(LocalDateTime votingDateTime) {
        this(null, votingDateTime);
    }

    public Vote(Integer id, LocalDateTime votingDateTime){
        super(id);
        this.votingDateTime = votingDateTime;
    }

    public Vote(Integer id, LocalDateTime votingDateTime, Restaurant restaurant){
        super(id);
        this.votingDateTime = votingDateTime;
        this.restaurant = restaurant;
    }

    public Vote(LocalDateTime votingDateTime, Restaurant restaurant){
        this.votingDateTime = votingDateTime;
        this.restaurant = restaurant;
    }

    public Vote(Integer id, LocalDateTime votingDateTime, Restaurant restaurant, User user){
        super(id);
        this.votingDateTime = votingDateTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(LocalDateTime votingDateTime, Restaurant restaurant, User user){
        this.votingDateTime = votingDateTime;
        this.restaurant = restaurant;
        this.user = user;
    }

    public LocalDateTime getVotingDateTime() {
        return votingDateTime;
    }

    public void setVotingDateTime(LocalDateTime votingDateTime) {
        this.votingDateTime = votingDateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
