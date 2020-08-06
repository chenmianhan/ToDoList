package com.oocl.todolist.service;

import com.oocl.todolist.exception.IllegalOperationException;
import com.oocl.todolist.exception.NoSuchIdException;
import com.oocl.todolist.model.ToDo;
import com.oocl.todolist.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDo addToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }

    public ToDo updateToDo(int id, ToDo oldToDo) throws IllegalOperationException, NoSuchIdException {

        if (id != oldToDo.getId()) throw new IllegalOperationException();
        if (!toDoRepository.findById(id).isPresent())
            throw new NoSuchIdException();

        return toDoRepository.save(oldToDo);
    }

    //ToDo
    public void deleteById(int id) throws NoSuchIdException {
        if (!toDoRepository.findById(id).isPresent())
            throw new NoSuchIdException();
        toDoRepository.deleteById(id);
    }
}
