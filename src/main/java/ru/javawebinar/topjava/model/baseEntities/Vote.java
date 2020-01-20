package ru.javawebinar.topjava.model.baseEntities;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity{
    protected Integer userId;

    protected Integer restaurantId;

    private LocalDateTime votingDateTime;

    public Vote() {
    }
}
