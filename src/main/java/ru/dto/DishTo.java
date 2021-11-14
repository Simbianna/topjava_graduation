package ru.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class DishTo extends BaseTo{

    private String name;

    private double price;

    public DishTo() {
    }

    public DishTo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public DishTo(Integer id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishTo that = (DishTo) o;
        return price == that.price &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name);
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
