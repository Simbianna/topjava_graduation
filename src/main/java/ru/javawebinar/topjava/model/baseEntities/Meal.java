package ru.javawebinar.topjava.model.baseEntities;


import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    private int price;

    private Restaurant restaurant;

    private Date added;

    public Meal() {
    }

    public Meal(int price, Restaurant restaurant, Date added) {
        this.price = price;
        this.restaurant = restaurant;
        this.added = added;
    }

    public Meal(int id, String name, int price, Restaurant restaurant, Date added) {
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

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
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
