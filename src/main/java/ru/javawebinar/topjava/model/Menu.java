package ru.javawebinar.topjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")//, cascade = CascadeType.REMOVE, orphanRemoval = true
    @OrderBy("added DESC")
    private Set<Dish> dishes;

 //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
  //  @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(Integer id) {
        super(id);
    }

    /*public Menu(Integer id, String name, Set<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }*/

    /*public Menu(Set<Dish> dishes) {
        this.dishes = dishes;
    }*/

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                '}';
    }
}
