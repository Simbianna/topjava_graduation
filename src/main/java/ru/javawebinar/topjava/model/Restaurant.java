package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "rating")
    @NotNull
    @Range(min = 0)
    private AtomicInteger todaysRating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true
    private List<Meal> lunchMeals;

    public Restaurant() {
    }

//    public Restaurant(int id, String name, AtomicInteger todaysRating, Map<String, Double> lunchMeals) {
//        super(id, name);
//        this.todaysRating = todaysRating;
//        this.lunchMeals = lunchMeals;
//    }


    public Restaurant(AtomicInteger todaysRating, List<Meal> lunchMeals) {
        this.todaysRating = todaysRating;
        this.lunchMeals = lunchMeals;
    }

    public Restaurant(int id, String name, AtomicInteger todaysRating, List<Meal> lunchMeals) {
        super(id, name);
        this.todaysRating = todaysRating;
        this.lunchMeals = lunchMeals;
    }

    public AtomicInteger getTodaysRating() {
        return todaysRating;
    }

    public void setTodaysRating(AtomicInteger todaysRating) {
        this.todaysRating = todaysRating;
    }

//    public Map<String, Double> getTodaysLunchMeals() {
//        return lunchMeals;
//    }
//
//    public void setTodaysLunchMeals(Map<String, Double> lunchMeals) {
//        this.lunchMeals = lunchMeals;
//    }

//    @Override
//    public String toString() {
//        return "Restaurant{" +
//                "todaysRating=" + todaysRating +
//                ", lunchMeals=" + lunchMeals +
//                '}';
//    }


    public List<Meal> getTodaysLunchMeals() {
        return lunchMeals;
    }

    public void setTodaysLunchMeals(List<Meal> lunchMeals) {
        this.lunchMeals = lunchMeals;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", todaysRating=" + todaysRating +
                ", lunchMeals=" + lunchMeals +
                '}';
    }
}
