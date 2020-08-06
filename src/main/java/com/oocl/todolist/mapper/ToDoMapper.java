package com.oocl.todolist.mapper;

import com.oocl.todolist.model.ToDo;

public class ToDoMapper {

    public ToDoResponse covertToDoToToDoResponse(ToDo toDo) {
        ToDoResponse toDoResponse=new ToDoResponse();
        toDoResponse.setContent(toDo.getContent());
        toDoResponse.setId(toDo.getId());
        toDoResponse.setStatus(toDoResponse.isStatus());
        return toDoResponse;
    }
}
