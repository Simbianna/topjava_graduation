package ru.javawebinar.topjava.to;

import java.time.LocalDate;
import java.util.Objects;

public class VoteTo extends BaseTo {
    private LocalDate votingDate;
    private RestaurantTo restaurant;

    public VoteTo() {
    }

    public VoteTo(Integer id) {
        super(id);
    }

    public VoteTo(Integer id, LocalDate votingDate) {
        super(id);
        this.votingDate = votingDate;
    }

    public VoteTo(Integer id, LocalDate votingDate, RestaurantTo restaurant) {
        super(id);
        this.votingDate = votingDate;
        this.restaurant = restaurant;
    }

    public LocalDate getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(LocalDate votingDate) {
        this.votingDate = votingDate;
    }

    public RestaurantTo getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantTo restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteTo that = (VoteTo) o;
        return
                Objects.equals(id, that.id) &&
                        Objects.equals(votingDate, that.votingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, votingDate);
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "votingDate=" + votingDate +
                ", restaurant=" + restaurant +
                '}';
    }
}
