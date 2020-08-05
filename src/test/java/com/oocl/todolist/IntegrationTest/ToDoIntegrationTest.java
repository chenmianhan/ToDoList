package com.oocl.todolist.IntegrationTest;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoIntegrationTest {
    @Autowired
    ToDoRepository toDoRepository;
    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        toDoRepository.deleteAll();
    }

    @Test
    void should_get_toDos_when_hit_get_toDos_endpoint_given_nothing() throws Exception {
        //given
        toDoRepository.save(new ToDo(1,"content1",false));
        toDoRepository.save(new ToDo(2,"content2",true));
        toDoRepository.save(new ToDo(3,"content3",false));

        //when
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3));
        //then
    }
}
