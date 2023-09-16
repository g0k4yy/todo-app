package com.gokay.todo.controller;

import com.gokay.todo.dto.*;
import com.gokay.todo.entity.Todo;
//import com.gokay.todo.entity.User;
import com.gokay.todo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(ToDoInput input) {
        Boolean isSaved = service.save(input);
        return ResponseEntity.ok(isSaved);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ToDoResponse>> getTodos() {
        return ResponseEntity.ok(service.findAllTodos());
    }

    /*
    @GetMapping("/list/{id}")
    public ResponseEntity<List<ToDoResponse>> getTodosofUser(@PathVariable Userdto input) {
        return ResponseEntity.ok(service.getTodosOfUser(input));
    }
    */

    @PutMapping("/list")
    public ResponseEntity<ToDoResponse> updateTodo(ToDoUpdate update_dto) {
        return ResponseEntity.ok(service.uptadeToDo(update_dto));
    }

    @DeleteMapping("/list/{todoId}")
    public ResponseEntity<Boolean> InputGet(@PathVariable int todoId) {
        return ResponseEntity.ok(service.delete(todoId));
    }
}



/*


     */

