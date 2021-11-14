package ru.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@RequiredArgsConstructor
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    @NotNull
    @Range(min = 0)
    private double price;

    @Column(name = "added", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime added = LocalDateTime.now();

    @Column(name = "isincludedinmenu", nullable = false, columnDefinition = "bool default false")
    private boolean included;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurant restaurant;


    public Dish(String name) {
        super(name);
    }

    public Dish(Integer id, String name, double price, LocalDateTime added, boolean included) {
        super(id, name);
        this.price = price;
        this.added = added;
        this.included = included;
    }

    public Dish(String name, double price, LocalDateTime added, boolean included) {
      this(null, name, price, added, included);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                ", included=" + included +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        if (!super.equals(o)) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.getPrice(), getPrice()) == 0 &&
                isIncluded() == dish.isIncluded() &&
                Objects.equals(getAdded(), dish.getAdded()) &&
                Objects.equals(getRestaurant(), dish.getRestaurant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPrice());
    }
}
