package com.oocl.todolist.handler;

import com.oocl.todolist.exception.IllegalOperationException;
import com.oocl.todolist.exception.NoSuchIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchIdException.class)
    void handleNoSuchDataException(NoSuchIdException noSuchDataException) {

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalOperationException.class)
    void handleIllegalOperationException(IllegalOperationException illegalOperationException) {
    }

}
