package com.oocl.todolist.service;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    public ToDoService( ToDoRepository toDoRepository){
        this.toDoRepository=toDoRepository;
    }
    public ToDo addToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<TODO> findAll() {
        return null;
    }
}
