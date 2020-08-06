package com.oocl.todolist.IntegrationTest;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


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

        ToDo newToDo=toDoRepository.save(new ToDo(1,"content1",false));
        int id=newToDo.getId();
        String toDoInfo="{\n" +
                "    \"id\":"+newToDo.getId()+",\n" +
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

    @Test
    void should_delete_to_do_when_hit_delete_to_do_endpoints_given_id_1() throws Exception {
        ToDo todo=toDoRepository.save(new ToDo(1,"content1",false));
        int id=todo.getId();
        //when
        mockMvc.perform(delete(("/todos/"+id)))
                .andExpect(status().isOk());

        //then
    }

    @Test
    void should_return_status_not_found_when_hit_delete_to_do_endpoints_given_not_exist_id() throws Exception {
        int id=1;

        //when
        mockMvc.perform(delete(("/todos/"+id)))
                .andExpect(status().isNotFound());
        //then
    }
    @Test
    void should_return_status_not_found_when_hit_update_to_do_endpoints_given_not_exist_id() throws Exception {
        //when
        //when
        int id=1;
        String toDoInfo="{\n" +
                "    \"id\":1,\n" +
                "    \"content\": \"content1\",\n" +
                "    \"status\": true\n" +
                "}";
        mockMvc.perform(patch(("/todos/"+id)).contentType(MediaType.APPLICATION_JSON)
                .content(toDoInfo))
                .andExpect(status().isNotFound());

        //then

    }
    @Test
    void should_return_status_forbidden_when_hit_update_to_do_endpoints_given_id_and_to_do() throws Exception {
        ToDo toDo=toDoRepository.save(new ToDo(null,"content1",false));
        int id=toDo.getId()+1;
        String toDoInfo="{\n" +
                "    \"id\":"+toDo.getId()+",\n" +
                "    \"content\": \"content1\",\n" +
                "    \"status\": true\n" +
                "}";
        mockMvc.perform(patch(("/todos/"+id)).contentType(MediaType.APPLICATION_JSON)
                .content(toDoInfo))
                .andExpect(status().isForbidden());

        //then
    }

}
