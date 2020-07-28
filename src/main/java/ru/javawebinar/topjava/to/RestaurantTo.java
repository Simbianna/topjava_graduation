package ru.javawebinar.topjava.to;

public class RestaurantTo extends BaseTo {

    private String name;
    private MenuTo lunchMenu;

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public RestaurantTo(Integer id, String name, MenuTo lunchMenu) {
        super(id);
        this.name = name;
        this.lunchMenu = lunchMenu;
    }

    public String getName() {
        return name;
    }

    public MenuTo getLunchMenu() {
        return lunchMenu;
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
