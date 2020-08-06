package com.oocl.todolist.controller;

import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ToDo> getAllToDo() {
        return toDoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDo addToDo(@RequestBody ToDo toDo) {
        return toDoService.addToDo(toDo);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ToDo updateToDo(@PathVariable int id, @RequestBody ToDo toDo) {
        return toDoService.updateToDo(id, toDo);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteToDo(@PathVariable int id){
        toDoService.deleteById(id);
    }


}
