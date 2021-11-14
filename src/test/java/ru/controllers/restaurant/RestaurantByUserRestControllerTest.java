package ru.controllers.restaurant;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.model.Restaurant;
import ru.repository.RestaurantRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantByUserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@MockBean
    private RestaurantRepository repository;

    @Test
    void getAll() throws Exception {
        this.mockMvc.perform(get("/restaurants"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        /*.andExpect(content().)*/;
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById() throws Exception {
        this.mockMvc.perform(get("/restaurants/2"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        /*.andExpect(content().)*/;
        verify(repository, times(1)).getById(2);
    }
}