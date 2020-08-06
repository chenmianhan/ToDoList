package com.oocl.todolist.mapper;

import com.oocl.todolist.model.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListMapperTest {
    private final ToDoMapper toDoMapper=new ToDoMapper();

    @Test
    void should_attribute_equal_when_convert_to_do_to_to_do_response_given_to_do() {
        //given
        ToDo toDo=new ToDo(1,"content",false);

        //when
        ToDoResponse toDoResponse=toDoMapper.covertToDoToToDoResponse(toDo);

        //then
        assertEquals(toDo.getId(),toDoResponse.getId());
        assertEquals(toDo.getContent(),toDoResponse.getContent());
        assertEquals(toDo.isStatus(),toDoResponse.isStatus());
    }
    @Test
    void should_attribute_equal_when_convert_to_do_request_to_to_do_given_to_do() {
        //given
        ToDoRequest toDoRequest =new ToDoRequest(1,"content",false);

        //when
        ToDo toDo=toDoMapper.covertToDoRequestToToDo(toDoRequest);

        //then
        assertEquals(toDoRequest.getId(),toDo.getId());
        assertEquals(toDoRequest.getContent(),toDo.getContent());
        assertEquals(toDoRequest.isStatus(),toDo.isStatus());
    }

}
