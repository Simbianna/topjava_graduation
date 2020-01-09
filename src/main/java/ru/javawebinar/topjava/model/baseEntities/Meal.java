package ru.javawebinar.topjava.model.baseEntities;


import javax.persistence.Entity;
import javax.persistence.Table;

import java.time.LocalDate;


@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    private int price;

    private Restaurant restaurant;

    private LocalDate added;

    public Meal() {
    }

    public Meal(int price, Restaurant restaurant, LocalDate added) {
        this.price = price;
        this.restaurant = restaurant;
        this.added = added;
    }

    public Meal(int id, String name, int price, Restaurant restaurant, LocalDate added) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", restaurant=" + restaurant +
                ", added=" + added +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
