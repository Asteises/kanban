package ru.asteises.kanban.telegram_bot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class CreateBoardCommand extends ServiceCommand {

    public CreateBoardCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String chatId = String.valueOf(chat.getId());
        String userName = user.getUserName();
        SendMessage message = new SendMessage();
        message.enableMarkdown(true); // включаем/отключаем поддержку режима разметки, чтобы управлять отображением текста и добавлять эмодзи
        message.setChatId(chatId);
        message.setText("Welcome, " + userName + " ! Here is a 'Kanban by Asteises'");

        //FIXME Получить chatId можно из user и зашить в код клавиатуры

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            log.error("Cannot send message with such parameters chatId: {}, userName: {}",
                    chatId, userName);
            throw new RuntimeException(e);
        }
    }
}
