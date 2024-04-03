package ru.asteises.kanban.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.UUID;

public interface SendMessageService {

    SendMessage userRegistration(Long chatId, Message command);

    SendMessage forBoardName(Long chatId);

    SendMessage afterSetBoardName(Long chatId, String boardName, UUID boardId);

    SendMessage forTaskName(Long chatId, String callbackData);

    SendMessage newTaskExample(Long chatId);

    SendMessage createNewTask(Long chatId, String taskName);

    SendMessage allUserTasks(Long chatId);

    SendMessage getTaskInfo(Long chatId, String callbackData);
}
