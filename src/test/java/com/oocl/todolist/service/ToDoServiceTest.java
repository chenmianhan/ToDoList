package com.oocl.todolist.service;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ToDoServiceTest {
    @Autowired
    private ToDoService toDoService;
    private ToDoRepository mockedToDoRepository;
    @BeforeEach
     void beforeEach() {
        mockedToDoRepository= Mockito.mock(ToDoRepository.class);
        toDoService=new ToDoService(mockedToDoRepository);

    }

    @Test
    void should_return_a_todo_with_id_and_save_todo_when_add_todo_given_a_todo() {
        //given
        ToDo toDo=new ToDo(null,"content1",false);
        ToDo mockedToDo=new ToDo(1,"content1",false);
        when(mockedToDoRepository.save(toDo)).thenReturn(mockedToDo);

        //when
        ToDo returnToDo=toDoService.addToDo(toDo);

        //then
        assertEquals(mockedToDo.getId(),returnToDo.getId());
        assertEquals(mockedToDo.getContent(),returnToDo.getContent());
        assertEquals(mockedToDo.isStatus(),returnToDo.isStatus());
    }
}
