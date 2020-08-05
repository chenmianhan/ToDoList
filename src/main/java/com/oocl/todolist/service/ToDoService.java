package com.oocl.todolist.service;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;

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

    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }

    public ToDo updateToDo(int id, ToDo oldToDo) {
        if(id==oldToDo.getId()&& toDoRepository.findById(id).isPresent())
        return toDoRepository.save(oldToDo);
        else return null;
    }

    public void deleteById(int id) {
        toDoRepository.deleteById(id);
    }
}
