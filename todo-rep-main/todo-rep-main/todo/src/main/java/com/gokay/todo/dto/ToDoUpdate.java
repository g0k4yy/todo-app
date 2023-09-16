package com.gokay.todo.dto;

import com.gokay.todo.entity.Priority;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ToDoUpdate {

    private Long ID;
    private String title;
    private String description;
    private String date;
    private Priority priority;
}