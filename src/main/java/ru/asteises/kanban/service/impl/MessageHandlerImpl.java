package ru.asteises.kanban.service.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.service.BoardService;
import ru.asteises.kanban.utils.AppText;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageHandlerImpl implements ru.asteises.kanban.service.MessageHandler {

    private final BoardService boardService;

    @Override
    public SendMessage handleMessage(Message message) throws NotFoundException {
        SendMessage sendMessage;
        String messageText = message.getText();
        Long chatId = message.getChatId();
        if (message.isUserMessage()) {
            if (messageText.toLowerCase().endsWith(AppText.BOARD)) {
                Board newBoard = boardService.createBoard(chatId, messageText);
                return new SendMessage(
                        message.getChatId().toString(),
                        "Board create! Board name: " + newBoard.getName());
            }
        }
        throw new NotFoundException("Not found type of message");
    }
}
