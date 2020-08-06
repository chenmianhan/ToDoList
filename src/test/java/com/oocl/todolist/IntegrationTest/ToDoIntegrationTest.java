package com.oocl.todolist.IntegrationTest;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void should_add_toDo_when_hit_post_toDo_endpoints_given_new_employee_info() throws Exception {
        //given
        String toDoInfo="{\n" +
                "    \"id\": null,\n" +
                "    \"content\": \"content12\",\n" +
                "    \"status\": false\n" +
                "}";

        //when
        mockMvc.perform(post(("/todos")).contentType(MediaType.APPLICATION_JSON)
                .content(toDoInfo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("content12"))
                .andExpect(jsonPath("$.status").value("false"));

        //then
    }

    @Test
    void should_update_toDo_when_hit_patch_toDo_endpoints_given_id_1_and_new_todo() throws Exception {
        //given
        int id=1;
        toDoRepository.save(new ToDo(1,"content1",false));
        String toDoInfo="{\n" +
                "    \"id\":1,\n" +
                "    \"content\": \"content1\",\n" +
                "    \"status\": true\n" +
                "}";

        //when
        mockMvc.perform(patch(("/todos/"+id)).contentType(MediaType.APPLICATION_JSON)
                .content(toDoInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("content1"))
                .andExpect(jsonPath("$.status").value("true"));

        //then
    }
}
