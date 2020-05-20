package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "rating")
  //  @NotNull
    @Range(min = 0)
    private AtomicInteger todaysRating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true
    private List<Dish> lunchMenu;

    public Restaurant() {
    }

    public Restaurant(int id, String name){
        super(id,name);
    }

    public Restaurant(String name){
        super(name);
    }

    public List<Dish> getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(List<Dish> lunchMenu) {
        this.lunchMenu = lunchMenu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "todaysRating=" + todaysRating +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
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
//TODO вот тут надо подумать
    public AtomicInteger getTodaysRating() {
        return Objects.requireNonNullElse(todaysRating,new AtomicInteger(0));
    }

    public void setTodaysRating(AtomicInteger todaysRating) {
        this.todaysRating = Objects.requireNonNullElse(todaysRating,new AtomicInteger(0));
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

//
//    public List<Dish> getTodaysLunchMeals() {
//        return lunchMenu;
//    }
//
//    public void setTodaysLunchMeals(List<Dish> lunchDishes) {
//        this.lunchMenu = lunchDishes;
//    }


}
