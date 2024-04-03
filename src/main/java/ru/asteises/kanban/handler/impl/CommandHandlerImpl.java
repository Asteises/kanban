package ru.asteises.kanban.handler.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.handler.CommandHandler;
import ru.asteises.kanban.service.SendMessageService;
import ru.asteises.kanban.telegram_bot.keyboard.BoardKeyboard;
import ru.asteises.kanban.utils.CommandText;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandHandlerImpl implements CommandHandler {
    private final BoardKeyboard boardKeyboard;
    private final SendMessageService sendMessageService;

    @Override
    public SendMessage handleCommand(Message command) throws NotFoundException {
        if (command.isCommand()) {
            Long chatId = command.getChatId();
            String commandText = command.getText();
            if (CommandText.START.equals(commandText)) {
                return sendMessageService.userRegistration(chatId, command);
            } else if (commandText.equals(CommandText.NEW_BOARD)) {
                return sendMessageService.forBoardName(chatId);
            } else if (commandText.equals(CommandText.ALL_BOARDS)) {
                return boardKeyboard.allUserBoards(chatId);
            } else if (commandText.startsWith(CommandText.NEW_TASK)) {
                if (commandText.length() <= CommandText.NEW_TASK.length()) {
                    return sendMessageService.newTaskExample(chatId);
                } else {
                    String taskName = commandText.substring(CommandText.NEW_TASK.length() + 1).trim();
                    return sendMessageService.createNewTask(chatId, taskName);
                }
            } else if (commandText.equals(CommandText.ALL_TASKS)) {
                return sendMessageService.allUserTasks(chatId);
            } else {
                return new SendMessage(chatId.toString(), "Message text: " + commandText);
            }
        }
        throw new NotFoundException("Not found type of message");
    }
}
