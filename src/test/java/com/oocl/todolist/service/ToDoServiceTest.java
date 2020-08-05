package com.oocl.todolist.service;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

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

    @Test
    void should_return_todo_list_when_get_all_todo_given_nothing() {
        //given
        List<ToDo> mockedToDos=new LinkedList<>();
        mockedToDos.add(new ToDo(1,"content2",false));
        when(mockedToDoRepository.findAll()).thenReturn(mockedToDos);

        //when
        List<ToDo> toDos=toDoService.findAll();

        //then
        assertEquals(mockedToDos.size(),toDos.size());
    }

    @Test
    void should_return_todo_when_update_todo_given_id_1_status_true() {
        //given
        ToDo oldToDo=new ToDo(1,"content1",false);
        ToDo mockedToDo=new ToDo(1,"content1",true);
        int id=1;

        //when
        ToDo newToDo=toDoService.updateToDo(id,oldToDo);

        //then
        assertEquals(mockedToDo.getId(),newToDo.getId());
        assertEquals(mockedToDo.isStatus(),newToDo.isStatus());
    }
}
