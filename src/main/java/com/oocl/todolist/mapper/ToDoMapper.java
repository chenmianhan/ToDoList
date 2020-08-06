package com.oocl.todolist.mapper;

import com.oocl.todolist.model.ToDo;

public class ToDoMapper {

    public ToDoResponse covertToDoToToDoResponse(ToDo toDo) {
        ToDoResponse toDoResponse=new ToDoResponse();
        toDoResponse.setContent(toDo.getContent());
        toDoResponse.setId(toDo.getId());
        toDoResponse.setStatus(toDo.isStatus());
        return toDoResponse;
    }

    public ToDo covertToDoRequestToToDo(ToDoRequest toDoRequest) {
        ToDo toDo=new ToDo();
        toDo.setContent(toDoRequest.getContent());
        toDo.setId(toDoRequest.getId());
        toDo.setStatus(toDoRequest.isStatus());
        return toDo;
    }
}
