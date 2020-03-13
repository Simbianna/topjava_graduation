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
    private List<Dish> lunchDishes;

    public Restaurant() {
    }

    public Restaurant(int id, String name){
        super(id,name);
    }

    public Restaurant(String name){
        super(name);
    }

//    public Restaurant(int id, String name, AtomicInteger todaysRating, Map<String, Double> lunchDishes) {
//        super(id, name);
//        this.todaysRating = todaysRating;
//        this.lunchDishes = lunchDishes;
//    }

//    public Restaurant(String name, List<Dish> lunchDishes) {
//        super(name);
//        this.lunchDishes = lunchDishes;
//    }
//
//    public Restaurant(String name, AtomicInteger todaysRating, List<Dish> lunchDishes) {
//        super(name);
//        this.todaysRating = todaysRating;
//        this.lunchDishes = lunchDishes;
//    }
//
//    public Restaurant(int id, String name, List<Dish> lunchDishes) {
//        super(id, name);
//        this.lunchDishes = lunchDishes;
//    }
//
//    public Restaurant(int id, String name, AtomicInteger todaysRating, List<Dish> lunchDishes) {
//        super(id, name);
//        this.todaysRating = todaysRating;
//        this.lunchDishes = lunchDishes;
//    }

    public AtomicInteger getTodaysRating() {
        return todaysRating;
    }

    public void setTodaysRating(AtomicInteger todaysRating) {
        this.todaysRating = todaysRating;
    }

//    public Map<String, Double> getTodaysLunchDishes() {
//        return lunchDishes;
//    }
//
//    public void setTodaysLunchMeals(Map<String, Double> lunchDishes) {
//        this.lunchDishes = lunchDishes;
//    }

//    @Override
//    public String toString() {
//        return "Restaurant{" +
//                "todaysRating=" + todaysRating +
//                ", lunchDishes=" + lunchDishes +
//                '}';
//    }


    public List<Dish> getTodaysLunchMeals() {
        return lunchDishes;
    }

    public void setTodaysLunchMeals(List<Dish> lunchDishes) {
        this.lunchDishes = lunchDishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", todaysRating=" + todaysRating +
                ", lunchDishes=" + lunchDishes +
                '}';
    }
}
