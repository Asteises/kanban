package ru.asteises.kanban.telegram_bot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class StartCommand extends ServiceCommand {

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        // включаем/отключаем поддержку режима разметки, чтобы управлять отображением текста и добавлять эмодзи
        message.enableMarkdown(true);
        String chatId = String.valueOf(chat.getId());
        String userName = user.getUserName();
        message.setChatId(chatId);
        message.setText("Welcome, " + userName + " ! Here is an Kanban by Asteises! Here's what you can do:");

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            log.error("Cannot send message with such parameters chatId: {}, userName: {}",
                    chatId, userName);
            throw new RuntimeException(e);
        }
    }
}
