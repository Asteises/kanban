package ru.asteises.kanban.telegram_bot.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.asteises.kanban.utils.AppText;
import ru.asteises.kanban.utils.ButtonText;
import ru.asteises.kanban.utils.CallbackText;

import java.util.ArrayList;
import java.util.List;

@Component
public class FirstPageKeyboard {

    public static SendMessage firstPageFullKeyBoard(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(AppText.AFTER_REGISTRATION);

        InlineKeyboardButton createBoardButton = new InlineKeyboardButton();
        createBoardButton.setText(ButtonText.CREATE_NEW_BOARD);
        createBoardButton.setCallbackData(CallbackText.CREATE_NEW_BOARD);

        InlineKeyboardButton createTaskButton = new InlineKeyboardButton();
        createTaskButton.setText(ButtonText.CREATE_NEW_TASK);
        createTaskButton.setCallbackData(CallbackText.CREATE_NEW_TASK);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row1.add(createBoardButton);
        row2.add(createTaskButton);
        rows.add(row1);
        rows.add(row2);
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        return message;
    }
}
