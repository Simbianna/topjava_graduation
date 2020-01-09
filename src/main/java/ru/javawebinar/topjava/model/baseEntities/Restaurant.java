package ru.javawebinar.topjava.model.baseEntities;

import javax.persistence.Entity;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
public class Restaurant extends AbstractBaseEntity {
    private AtomicInteger todaysRating;

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
                "todaysRating=" + todaysRating +
                ", lunchMeals=" + lunchMeals +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
