package ru.asteises.kanban.service;

import ru.asteises.kanban.model.dto.Task;

public interface TaskService {

    Task createTask(Long userChatId, String taskName);
    Task createTask(Long userChatId, String taskName, String boardId);
}
