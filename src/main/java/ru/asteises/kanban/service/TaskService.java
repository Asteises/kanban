package ru.asteises.kanban.service;

import ru.asteises.kanban.model.dto.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Long chatId, String taskName);
    Task createTask(Long chatId, String taskName, String boardId);
    List<Task> getAllActualUserTasks(Long chatId);
    Task getTaskById(Long chatId, String taskId);
}
