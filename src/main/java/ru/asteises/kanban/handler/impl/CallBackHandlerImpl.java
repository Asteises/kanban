package ru.asteises.kanban.handler.impl;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.asteises.kanban.handler.CallBackHandler;
import ru.asteises.kanban.service.SendMessageService;
import ru.asteises.kanban.utils.CallbackText;

@Slf4j
@RequiredArgsConstructor
@Component
public class CallBackHandlerImpl implements CallBackHandler {
    private final SendMessageService sendMessageService;

    @Override
    public SendMessage handleCallBack(CallbackQuery callbackQuery) throws NotFoundException {
        Long chatId = callbackQuery.getMessage().getChatId();
        String callbackData = callbackQuery.getData();
        if (callbackData.endsWith(CallbackText.CREATE_NEW_BOARD)) {
            return sendMessageService.forBoardName(chatId);

        } else if (callbackData.endsWith(CallbackText.ALL_BOARD_TASKS)) {
            return new SendMessage(chatId.toString(), "Тут будут все таски");

        } else if (callbackData.endsWith(CallbackText.CREATE_NEW_TASK)) {
            return sendMessageService.forTaskName(chatId, callbackData); // в callback зашит id board
        } else if (callbackData.endsWith(CallbackText.GET_TASK)) { // в callback зашит id task
            return sendMessageService.getTaskInfo(chatId, callbackData);
        }
        throw new NotFoundException("Not found type of callback");
    }
}
