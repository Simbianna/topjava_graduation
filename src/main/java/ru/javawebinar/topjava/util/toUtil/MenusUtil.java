package ru.javawebinar.topjava.util.toUtil;

import ru.javawebinar.topjava.model.Menu;
import ru.javawebinar.topjava.to.MenuTo;

import java.util.Objects;

public class MenusUtil {
    private MenusUtil() {
    }

    static MenuTo asTo(Menu menu) {
        return new MenuTo(menu.getId(), Objects.requireNonNullElse(DishesUtil.asTo(menu.getDishes()),null));
    }
}
