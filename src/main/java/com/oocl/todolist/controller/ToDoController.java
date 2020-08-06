package com.oocl.todolist.controller;

import com.oocl.todolist.exception.IllegalOperationException;
import com.oocl.todolist.exception.NoSuchIdException;
import com.oocl.todolist.mapper.ToDoMapper;
import com.oocl.todolist.mapper.ToDoRequest;
import com.oocl.todolist.mapper.ToDoResponse;
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
    private final ToDoMapper toDoMapper;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
        toDoMapper = new ToDoMapper();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ToDo> getAllToDo() {
        return toDoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoResponse addToDo(@RequestBody ToDoRequest toDoRequest) {

        return toDoMapper.covertToDoToToDoResponse(
                toDoService.addToDo(toDoMapper.covertToDoRequestToToDo(toDoRequest)));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ToDoResponse updateToDo(@PathVariable int id, @RequestBody ToDoRequest toDoRequest) throws IllegalOperationException, NoSuchIdException {
        return toDoMapper.covertToDoToToDoResponse(
                toDoService.updateToDo(id, toDoMapper.covertToDoRequestToToDo(toDoRequest)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteToDo(@PathVariable int id) throws NoSuchIdException {
        toDoService.deleteById(id);
    }


}
