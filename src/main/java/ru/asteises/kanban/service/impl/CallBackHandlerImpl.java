package ru.asteises.kanban.service.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.service.CallBackHandler;
import ru.asteises.kanban.service.UserService;
import ru.asteises.kanban.utils.AppText;

@Slf4j
@RequiredArgsConstructor
@Component
public class CallBackHandlerImpl implements CallBackHandler {

    private final UserService userService;

    @Override
    public SendMessage handleCallBack(CallbackQuery callbackQuery) throws NotFoundException {
        Long chatId = callbackQuery.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        if ("CREATE_BOARD".equals(callbackQuery.getData())) {
            sendMessage.setChatId(chatId);
            sendMessage.setText(AppText.SET_BOARD_NAME);
            return sendMessage;
        } else if ("CREATE_TASK".equals(callbackQuery.getData())) {

        }
        throw new NotFoundException("Not found type of callback");
    }
}
