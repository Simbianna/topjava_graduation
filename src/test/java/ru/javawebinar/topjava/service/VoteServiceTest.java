package ru.javawebinar.topjava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.util.exception.ErrorType;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.javawebinar.topjava.testData.RestaurantTestData.ITALIAN_ID;
import static ru.javawebinar.topjava.testData.UserTestData.*;
import static ru.javawebinar.topjava.testData.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService service;

    @Test
    void get() throws Exception {
        assertMatch(service.get(VOTE_ID, USER_ID), USER_VOTE_D1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1, USER_ID));
    }

    @Test
    void delete() {
        service.delete(VOTE_ID, USER_ID);
        List<Vote> v = ALL_VOTES_WITHOUT_DELETED;
        assertMatch(service.getAll(), ALL_VOTES_WITHOUT_DELETED);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(1, USER_ID));
    }

    @Test
    void getAllForUser() {
        assertMatch(service.getAllForUser(USER_ID), ALL_VOTES_BY_USER1);
    }

    @Test
    void getLastForUser() {
        assertMatch(service.getLastForUser(USER_ID), USER_VOTE_D2);
    }

    @Test
    void getAllForRestaurant() {
        assertMatch(service.getAllForRestaurant(ITALIAN_ID, ADMIN_ID), ALL_VOTES_FOR_ITALIAN_SORTED);
    }

    @Test
    void getAllForRestaurantForOneDay() {
        assertMatch(service.getAllForRestaurantForOneDay(ITALIAN_ID, CHECK_DATE_TIME), ALL_VOTES_FOR_ITALIAN_CHECK_DATE);
    }

    @Test
    void getAllForRestaurantBetweenDateTimes() {
        assertMatch(service.getAllForRestaurantBetweenDateTimes(ITALIAN_ID, START_DATE_TIME, END_DATE_TIME), ALL_VOTES_FOR_ITALIAN_BETWEEN_DATE_TIMES);
    }

    @Test
    void update() {
        Vote updated = ITALIAN_VOTE_UPDATED;
        service.update(updated, USER_ID);
        assertMatch(service.get(VOTE_ID, USER_ID), updated);
    }

    @Test
    void updateNotFound() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(USER_VOTE_D1, ADMIN_ID));
        String msg = exception.getMessage();
        assertTrue(msg.contains(ErrorType.DATA_NOT_FOUND.name()));
        assertTrue(msg.contains(NotFoundException.NOT_FOUND_EXCEPTION));
        assertTrue(msg.contains(String.valueOf(VOTE_ID)));
    }

    @Test
    void create() {
        Vote newVote = ITALIAN_VOTE_CREATED;
        Vote created = service.create(newVote, USER_ID);
        newVote.setId(created.getId());
        assertMatch(newVote, created);
        assertMatch(service.getAllForRestaurant(ITALIAN_ID, USER_ID), ALL_VOTES_FOR_ITALIAN_WITH_CREATED);

    }

    @Test
    void getWithUser() {
    }
}