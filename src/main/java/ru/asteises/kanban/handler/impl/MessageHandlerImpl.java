package ru.asteises.kanban.handler.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.handler.MessageHandler;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.service.BoardService;
import ru.asteises.kanban.service.SendMessageService;
import ru.asteises.kanban.utils.AppText;
import ru.asteises.kanban.utils.CommonText;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageHandlerImpl implements MessageHandler {

    private final BoardService boardService;
    private final SendMessageService sendMessageService;

    @Override
    public SendMessage handleMessage(Message message) throws NotFoundException {
        SendMessage sendMessage;
        String messageText = message.getText();
        Long chatId = message.getChatId();
        if (message.isUserMessage()) {

            //FIXME Нужно добавить валидацию сообщения пользователя и сообщения об ошибках

            if (messageText.toLowerCase().endsWith(CommonText.BOARD)) { // тут messageText это название board
                Board newBoard = boardService.createBoard(chatId, messageText);
                return sendMessageService.afterSetBoardName(chatId, messageText, newBoard.getId());
            }

            else if (messageText.toLowerCase().endsWith(CommonText.TASK)) { // тут messageText это название task

            }
        }
        throw new NotFoundException("Not found type of message");
    }
}