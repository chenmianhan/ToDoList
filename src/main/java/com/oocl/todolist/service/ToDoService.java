package com.oocl.todolist.service;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    public ToDoService( ToDoRepository toDoRepository){
        this.toDoRepository=toDoRepository;
    }
    public ToDo addToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }
}
