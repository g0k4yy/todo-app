package com.gokay.todo.repository;
import com.gokay.todo.entity.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRep extends JpaRepository<Todo, Long> {

        @Query("SELECT t FROM Todo t WHERE t.id = ?1")
        Todo getById(Long ID);

        //@Query("SELECT t from Todo t WHERE t.user.ID = ?1")
        //List<Todo> getByUserID(Long ID);

}

