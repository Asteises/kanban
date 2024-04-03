package ru.asteises.kanban.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private UUID id;
    private String name;
    private String description;
    private Board board;
    private User creator;
    private User executor;
}
