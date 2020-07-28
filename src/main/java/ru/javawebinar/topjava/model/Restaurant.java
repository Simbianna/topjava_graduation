package ru.javawebinar.topjava.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;


@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToOne(fetch = FetchType.LAZY)//, cascade = CascadeType.REMOVE, orphanRemoval = true
    private Menu menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name);
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

   /* public Restaurant(int id, String name, Menu menu) {
        super(id, name);
        this.menu = menu;
    }*/

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
