package com.oocl.todolist.controller;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.serviceTest.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    private final ToDoService toDoService;
    public ToDoController(ToDoService toDoService){
        this.toDoService=toDoService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ToDo> getAllToDo() {
        return toDoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDo getAllToDo( @RequestBody ToDo toDo)
    {
        System.out.println(toDo.getContent());
        System.out.println(toDo.isStatus());
        return toDoService.addToDo(toDo);
    }
}
