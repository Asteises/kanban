package ru.asteises.kanban.telegram_bot.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class FirstPageKeyboard {

    public static SendMessage firstPageFullKeyBoard(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Here's what you can do:");
        InlineKeyboardButton createBoardButton = new InlineKeyboardButton();
        createBoardButton.setText("Create a board");
        createBoardButton.setCallbackData("CREATE_BOARD");
        InlineKeyboardButton joinBoardButton = new InlineKeyboardButton();
        joinBoardButton.setText("Join to board");
        joinBoardButton.setCallbackData("JOIN_BOARD");
        InlineKeyboardButton createTaskButton = new InlineKeyboardButton();
        createTaskButton.setText("Create a task");
        createTaskButton.setCallbackData("CREATE_TASK");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row1.add(createBoardButton);
        row1.add(joinBoardButton);
        row2.add(createTaskButton);

        rows.add(row1);
        rows.add(row2);

        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        return message;
    }
}
