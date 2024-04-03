package ru.asteises.kanban.handler;

import javassist.NotFoundException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallBackHandler {

    SendMessage handleCallBack(CallbackQuery callbackQuery) throws NotFoundException;
}
