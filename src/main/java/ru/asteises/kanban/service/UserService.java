package ru.asteises.kanban.service;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.model.dto.User;

public interface UserService {

    User createUser(Long chatId, CallbackQuery callbackQuery);

    User createUser(Long chatId, Message message);

    User getUserByChatId(Long chatId);
}
