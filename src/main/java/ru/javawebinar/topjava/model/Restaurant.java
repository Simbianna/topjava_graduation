package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "rating")
  //  @NotNull
    @Range(min = 0)
    private AtomicInteger rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true
    @OrderBy("added DESC")
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(int id, String name){
        super(id,name);
    }

    public Restaurant(String name){
        super(name);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "rating=" + rating +
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
    public AtomicInteger getRating() {
        return Objects.requireNonNullElse(rating,new AtomicInteger(0));
    }

    public void setRating(AtomicInteger todaysRating) {
        this.rating = Objects.requireNonNullElse(todaysRating,new AtomicInteger(0));
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
//        return dishes;
//    }
//
//    public void setTodaysLunchMeals(List<Dish> lunchDishes) {
//        this.dishes = lunchDishes;
//    }


}
