package ru.dto;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class RestaurantTo extends BaseTo {
    private String name;
    private List<DishTo> lunchMenu;

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public RestaurantTo(Integer id, String name, List<DishTo> lunchMenu) {
        super(id);
        this.name = name;
        this.lunchMenu = lunchMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo that = (RestaurantTo) o;
        return
                Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lunchMenu=" + lunchMenu +
                '}';
    }
}
