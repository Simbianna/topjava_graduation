package ru.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "added"}, name = "votes_unique_user_added_idx")})
public class Vote extends AbstractBaseEntity {

    @Column(name = "added", nullable = false, columnDefinition = "date default today()")
    @NotNull
    private LocalDate votingDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull//(groups = View.Persist.class)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    @JsonBackReference
    private User user;

    public Vote() {
    }

    public Vote(Integer id, LocalDate votingDate, Restaurant restaurant, User user) {
        super(id);
        this.votingDate = votingDate;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(Integer id, LocalDate votingDate, Restaurant restaurant) {
        super(id);
        this.votingDate = votingDate;
        this.restaurant = restaurant;
    }

    public Vote(LocalDate votingDate, Restaurant restaurant, User user) {
        this(null, votingDate, restaurant, user);
    }

    public Vote(LocalDate votingDate, Restaurant restaurant) {
        this(null, votingDate, restaurant);
    }

    public LocalDate getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(LocalDate votingDateTime) {
        this.votingDate = votingDateTime;
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

    @Override
    public String toString() {
        return "Vote{" +
                "votingDate=" + votingDate +
                ", restaurant=" + restaurant +
                ", user=" + user +
                ", id=" + id +
                '}';
    }
}
