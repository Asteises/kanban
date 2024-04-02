package ru.asteises.kanban.telegram_bot.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardKeyboard {

    public static SendMessage setBoardNameKeyboard(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Now you need to name your board. Its can be any name less then 100 chars and 'board' at the end: first_board or AmmyBoard");

        InlineKeyboardButton setNameBoardButton = new InlineKeyboardButton();
        setNameBoardButton.setText("Set board name");
        setNameBoardButton.setCallbackData("SET_BOARD_NAME");

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(setNameBoardButton);
        rows.add(row);
        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }
}
