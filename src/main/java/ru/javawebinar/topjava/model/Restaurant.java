package ru.javawebinar.topjava.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.springframework.context.annotation.Lazy;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {


    /*
    @Formula("(select count(v.id) as rating from restaurants as r left join votes as v on r.id = v.restaurant_id where r.id = id and v.restaurant_id = id group by r.id)")
    */
    @Transient
    @Lazy
    private Long rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true
    @OrderBy("added DESC")
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    public Restaurant(int id, String name, Long rating) {
        super(id, name);
        this.rating = rating;
    }

    public Restaurant(String name) {
        super(name);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = Objects.requireNonNullElse(rating, rating);
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
