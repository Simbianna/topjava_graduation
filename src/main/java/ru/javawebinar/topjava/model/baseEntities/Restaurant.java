package ru.javawebinar.topjava.model.baseEntities;

import javax.persistence.Entity;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
public class Restaurant extends AbstractBaseEntity {
    private AtomicInteger rating;
//    private Map<String, Double> lunchMeals;
    private List<Meal> lunchMeals;

    public Restaurant() {
    }

//    public Restaurant(int id, String name, AtomicInteger rating, Map<String, Double> lunchMeals) {
//        super(id, name);
//        this.rating = rating;
//        this.lunchMeals = lunchMeals;
//    }


    public Restaurant(AtomicInteger rating, List<Meal> lunchMeals) {
        this.rating = rating;
        this.lunchMeals = lunchMeals;
    }

    public Restaurant(int id, String name, AtomicInteger rating, List<Meal> lunchMeals) {
        super(id, name);
        this.rating = rating;
        this.lunchMeals = lunchMeals;
    }

    public AtomicInteger getTodaysRating() {
        return rating;
    }

    public void setTodaysRating(AtomicInteger rating) {
        this.rating = rating;
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
//                "rating=" + rating +
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
                "rating=" + rating +
                ", lunchMeals=" + lunchMeals +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
