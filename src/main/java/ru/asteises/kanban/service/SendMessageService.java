package ru.asteises.kanban.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.UUID;

public interface SendMessageService {

    SendMessage forBoardName(Long chatId);

    SendMessage afterSetBoardName(Long chatId, String boardName, UUID boardId);

    SendMessage forTaskName(Long chatId, String callbackData);
}
