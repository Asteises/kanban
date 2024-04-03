package ru.asteises.kanban.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.mapper.SendMessageMapper;
import ru.asteises.kanban.model.dto.Task;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.service.SendMessageService;
import ru.asteises.kanban.service.TaskService;
import ru.asteises.kanban.service.UserService;
import ru.asteises.kanban.telegram_bot.keyboard.BoardKeyboard;
import ru.asteises.kanban.telegram_bot.keyboard.FirstPageKeyboard;
import ru.asteises.kanban.telegram_bot.keyboard.TaskKeyboard;
import ru.asteises.kanban.utils.AppText;
import ru.asteises.kanban.utils.CommonText;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SendMessageServiceImpl implements SendMessageService {

    private final UserService userService;
    private final TaskService taskService;

    @Override
    public SendMessage userRegistration(Long chatId, Message command) {
        User user;
        try {
            user = userService.getUserByChatId(chatId);
            log.info("User exist: {}", user);
        } catch (RuntimeException exception) {
            user = userService.createUser(chatId, command);
            log.info("return User after create: {}", user);
        }
        return FirstPageKeyboard.firstPageFullKeyBoard(chatId);
    }

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
        return TaskKeyboard.setTaskNameButton(chatId, newTask.getId().toString());
    }

    @Override
    public SendMessage newTaskExample(Long chatId) {
        return new SendMessage(chatId.toString(), AppText.NEW_TASK_EXAMPLE);
    }

    @Override
    public SendMessage createNewTask(Long chatId, String taskName) {
        Task newTask = taskService.createTask(chatId, taskName);
        return TaskKeyboard.mainTaskKeyboard(chatId, newTask);
    }

    @Override
    public SendMessage allUserTasks(Long chatId) {
        List<Task> tasks = taskService.getAllActualUserTasks(chatId);
        return TaskKeyboard.getAllUserTasks(chatId, tasks);
    }

    @Override
    public SendMessage getTaskInfo(Long chatId, String callbackData) {
        String taskId = callbackData.substring(0, callbackData.indexOf(CommonText.CALLBACK_DELIMITER));
        Task task = taskService.getTaskById(chatId, taskId);
        return TaskKeyboard.mainTaskKeyboard(chatId, task);
    }

}
