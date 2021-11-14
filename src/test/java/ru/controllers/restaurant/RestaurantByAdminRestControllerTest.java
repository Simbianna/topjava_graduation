package ru.controllers.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.model.Restaurant;
import ru.repository.RestaurantRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.TestUtil.readFromJson;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "ADMIN")
class RestaurantByAdminRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    private RestaurantRepository repository;

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/admin/restaurants"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        /*.andExpect(content().)*/;
        //verify(repository, times(1)).findAll();
    }

    @Test
    void getById() throws Exception {
        this.mockMvc.perform(get("/admin/restaurants/2"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        /*.andExpect(content().)*/;
        //verify(repository, times(1)).getById(2);
    }

    @Test
    void deleteById() throws Exception {
        this.mockMvc.perform(delete("/admin/restaurants/1"))
                .andDo(print())
                .andExpect(status().isNoContent())
        /*.andExpect(content().)*/;
        //verify(repository, times(1)).deleteById(2);
    }

    @Test
    void create() throws Exception {
        Restaurant created = new Restaurant();
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post("/admin/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": 2,\n" +
                        "    \"name\": \"Italian restaurant\",\n" +
                        "    \"lunchMenu\": [\n" +
                        "        {\n" +
                        "            \"id\": 4,\n" +
                        "            \"name\": \"Daily pizza\",\n" +
                        "            \"price\": 500.0\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 5,\n" +
                        "            \"name\": \"Chicken Parmigiana\",\n" +
                        "            \"price\": 400.0\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 6,\n" +
                        "            \"name\": \"Fried Mozzarella\",\n" +
                        "            \"price\": 750.5\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"id\": 10,\n" +
                        "            \"name\": \"Roasted salmon\",\n" +
                        "            \"price\": 700.0\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
        );

        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());
        assertEquals(returned, created);
    }

  /*  @Test
    void update() {
    }*/
}