package ru.util;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Util {
    private Util() {
    }

    public <T extends Comparable<? super T>> boolean isBetween(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) <= 0);
    }
}
