package com.gokay.todo.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.gokay.todo.dto.*;
import com.gokay.todo.entity.Todo;
import com.gokay.todo.repository.ToDoRep;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoService {


    private final ToDoRep rep;


    public boolean save(ToDoInput input) {
        Todo todo = Todo.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .priority(input.getPriority())
                .date(input.getDate()).build();
        try {
            rep.save(todo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ToDoResponse> findAllTodos() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 1);
        System.out.println("ÖNCE");
        Page<Todo> allProducts = rep.findAll(firstPageWithTwoElements);
        System.out.println("Sonra");
        List<Todo> listOfTodos = rep.findAll(); // exception

        List<ToDoResponse> responseList = listOfTodos.stream()
                .map(toDo -> {
                    ToDoResponse responseDto = new ToDoResponse();
                    responseDto.setID(toDo.getId());
                    responseDto.setTitle(toDo.getTitle());
                    responseDto.setDescription(toDo.getDescription());
                    responseDto.setPriority(toDo.getPriority());
                    responseDto.setDate(toDo.getDate());
                    return responseDto;
                })
                .collect(Collectors.toList());

        return responseList;
    }

    public ToDoResponse uptadeToDo(ToDoUpdate dto)    {
        Todo todo = rep.getById(dto.getID());
            todo.setDate(dto.getDate());
            todo.setDescription(dto.getDescription());
            todo.setTitle(dto.getTitle());
            todo.setPriority(dto.getPriority());

        ToDoResponse response = ToDoResponse.builder()
                .ID(dto.getID())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .date(dto.getDate()).build();


        rep.save(todo);
        return response;


    }
    /**public List<ToDoResponse> getTodosOfUser(Userdto input) {
        List<Todo> listOfTodos = rep.getByUserID(input.getID());

        List<ToDoResponse> responseList = listOfTodos.stream()
                .map(toDo -> {
                    ToDoResponse responseDto = new ToDoResponse();
                    responseDto.setID(toDo.getId());
                    responseDto.setTitle(toDo.getTitle());
                    responseDto.setDescription(toDo.getDescription());
                    responseDto.setPriority(toDo.getPriority());
                    responseDto.setDate(toDo.getDate());
                    return responseDto;
                })
                .collect(Collectors.toList());


        return responseList;
    }
     */
     public boolean delete(int id) {
         long longValue = (long) id;
         Todo todo = rep.getById(longValue);
         rep.delete(todo);
         return true;

    }

}
