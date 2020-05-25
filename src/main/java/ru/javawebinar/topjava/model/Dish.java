package ru.javawebinar.topjava.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity{

    @Column(name = "price")
    @NotNull
    @Range(min = 0)
    private double price;

    @Column(name = "added", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime added;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    public Dish() {
    }

//    public Dish (String name, double price, LocalDateTime added) {
//        this(null, name, price, added);
//    }
//
//    public Dish(Integer id, String name, double price, LocalDateTime added) {
//        super(id, name);
//        this.price = price;
//        this.added = added;
//    }

    public Dish (String name, double price, LocalDateTime added, Restaurant restaurant) {
        this(null, name, price, added, restaurant);
    }

    public Dish(Integer id, String name, double price, LocalDateTime added, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.added = added;
        this.restaurant = restaurant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getAdded() {
        return added;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                ", added=" + added +
                '}';
    }
}
