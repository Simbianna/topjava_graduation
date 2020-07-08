package ru.javawebinar.topjava.model;


import net.bytebuddy.implementation.bind.annotation.Default;
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


   // @Formula("(select count(v.id) as rating from restaurants as r left join votes as v on r.id = v.restaurant_id where r.id = id and v.restaurant_id = id group by r.id)")

    @Lazy
 //   @Formula("(select count(v.id) as rating from restaurants as r left join votes as v on r.id = v.restaurant_id where r.id = id and v.restaurant_id = id group by r.id)")
    private Long rating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true
    @OrderBy("added DESC")
    private List<Dish> dishes;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true
    private List<Vote> votes;*/

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

   // @Access(AccessType.PROPERTY)
 //   @Formula("(select count(v.id) as rating from restaurants as r left join votes as v on r.id = v.restaurant_id where r.id = id and v.restaurant_id = id group by r.id)")
    public Long getRating() {
        return rating;
    }

 //   @Access(AccessType.PROPERTY)
    public void setRating(Long rating) {
        this.rating = rating;
    }

    /* public List<Vote> getVotes() {
        return votes;
    }*/

    @Override
    public String toString() {
        return "Restaurant{" +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
