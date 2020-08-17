package ru.javawebinar.topjava.web.json;

import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.Vote;
import ru.javawebinar.topjava.testData.UserTestData;

import ru.javawebinar.topjava.model.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.javawebinar.topjava.testData.VoteTestData.*;


class JsonUtilTest {

 /*@Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(USER_VOTE_D1);
        System.out.println(json);
        Vote vote = JsonUtil.readValue(json, Vote.class);
        assertMatch(vote, USER_VOTE_D1);
    }

    @Test
    void readWriteValues() throws Exception {
        String json = JsonUtil.writeValue(ALL_VOTES);
        System.out.println(json);
        List<Vote> meals = JsonUtil.readValues(json, Vote.class);
        assertMatch(meals, ALL_VOTES);
    }*/

    @Test
    void writeOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.USER, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}