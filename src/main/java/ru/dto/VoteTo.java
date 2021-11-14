package ru.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
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
