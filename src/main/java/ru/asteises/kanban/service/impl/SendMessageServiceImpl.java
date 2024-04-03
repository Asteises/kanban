package ru.asteises.kanban.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.ReplyParameters;
import ru.asteises.kanban.mapper.SendMessageMapper;
import ru.asteises.kanban.model.dto.Task;
import ru.asteises.kanban.service.BoardService;
import ru.asteises.kanban.service.SendMessageService;
import ru.asteises.kanban.service.TaskService;
import ru.asteises.kanban.telegram_bot.keyboard.BoardKeyboard;
import ru.asteises.kanban.utils.AppText;
import ru.asteises.kanban.utils.CommonText;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SendMessageServiceImpl implements SendMessageService {
    private final TaskService taskService;

    @Override
    public SendMessage forBoardName(Long chatId) {
        return SendMessageMapper.INSTANCE.toSendMessage(chatId, AppText.SET_BOARD_NAME);
    }

    @Override
    public SendMessage afterSetBoardName(Long chatId, String boardName, UUID boardId) {
        return BoardKeyboard.infoBoardKeyboard(chatId, boardName, boardId);
    }

    @Override
    public SendMessage forTaskName(Long chatId, String callbackData) {
        String boardId = callbackData.substring(0, callbackData.indexOf(CommonText.CALLBACK_DELIMITER));
        Task newTask = taskService.createTask(chatId, CommonText.TASK, boardId);
        log.info("newTask: {}", newTask);
        return new SendMessage(chatId.toString(), "Таска для доски: " + boardId);
    }

}
