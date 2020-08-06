package ru.javawebinar.topjava.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    @NotNull
    @Range(min = 0)
    private double price;

    @Column(name = "added", nullable = false)
 /*   @NotNull*/
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime added;

    @Column(name = "isIncludedInMenu", nullable = false, columnDefinition = "bool default false")
    private boolean included;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String name) {
        super(name);
    }

    public Dish(String name, double price) {
        super(name);
        this.price = price;
    }

    public Dish(int id, String name, double price) {
        super(id, name);
        this.price = price;
    }

    public Dish(int id, String name, double price, LocalDateTime added) {
        super(id, name);
        this.price = price;
        this.added = added;
    }

    public Dish(int id, String name, double price, LocalDateTime added, boolean included) {
        super(id, name);
        this.price = price;
        this.added = added;
        this.included = included;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getAdded() {
        return added;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }

    public void setIncluded(boolean included){this.included = included;}

    public boolean isIncluded(){return included;}

    public void setRestaurant(Restaurant restaurant){this.restaurant = restaurant;}

    public Restaurant getRestaurant(){return restaurant;}

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                ", included=" + included +
                '}';
    }

   /* public Dish(String name, double price, Restaurant restaurant) {
        this(null, name, price, restaurant);
    }

    public Dish(Integer id, String name, double price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }*/

    /*public Dish(Integer id, String name, double price, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }
*/
//    public Dish (String name, double price, LocalDateTime added) {
//        this(null, name, price, added);
//    }
//
//    public Dish(Integer id, String name, double price, LocalDateTime added) {
//        super(id, name);
//        this.price = price;
//        this.added = added;
//    }

  /*  public Dish (String name, double price, LocalDateTime added, Restaurant restaurant) {
        this(null, name, price, added, restaurant);
    }*/

  /*  public Dish(Integer id, String name, double price, LocalDateTime added, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.added = added;
        this.restaurant = restaurant;
    }*/



   /* public LocalDateTime getAdded() {
        return added;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }*/


}
