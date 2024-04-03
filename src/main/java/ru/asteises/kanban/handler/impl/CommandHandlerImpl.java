package ru.asteises.kanban.handler.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.service.BoardService;
import ru.asteises.kanban.handler.CommandHandler;
import ru.asteises.kanban.service.SendMessageService;
import ru.asteises.kanban.service.UserService;
import ru.asteises.kanban.telegram_bot.keyboard.FirstPageKeyboard;
import ru.asteises.kanban.utils.CommandText;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommandHandlerImpl implements CommandHandler {

    private final UserService userService;
    private final BoardService boardService;
    private final SendMessageService sendMessageService;

    @Override
    public SendMessage handleCommand(Message command) throws NotFoundException {
        if (command.isCommand()) {
            SendMessage sendMessage;
            Long userChatId = command.getChatId();
            String commandText = command.getText();
            if (CommandText.START.equals(commandText)) {
                User user = userService.createUser(userChatId, command);
                log.info("return User after create: {}", user);
                sendMessage = FirstPageKeyboard.firstPageFullKeyBoard(userChatId);
                return sendMessage;
            }
            else if (CommandText.NEW_BOARD.equals(commandText)) {
                return sendMessageService.forBoardName(userChatId);
            }

            else {
                return new SendMessage(userChatId.toString(), "Message text: " + commandText);
            }
        }
        throw new NotFoundException("Not found type of message");
    }
}
