package com.oocl.todolist.mapper;

public class ToDoResponse {
    private Integer id;
    private String content;

    public ToDoResponse(Integer id, String content, boolean status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    private boolean status;

    public ToDoResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
