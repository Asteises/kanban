package ru.asteises.kanban.telegram_bot.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.asteises.kanban.utils.AppText;
import ru.asteises.kanban.utils.ButtonText;
import ru.asteises.kanban.utils.CallbackText;
import ru.asteises.kanban.utils.CommonText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BoardKeyboard {

    public static SendMessage infoBoardKeyboard(long chatId, String boardName, UUID boardId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(AppText.BORD_INFO_WELCOME + boardName);

        InlineKeyboardButton allBoardTasks = new InlineKeyboardButton();
        allBoardTasks.setText(ButtonText.ALL_BOARD_TASKS);
        allBoardTasks.setCallbackData(boardId + CommonText.CALLBACK_DELIMITER + CallbackText.ALL_BOARD_TASKS);

        InlineKeyboardButton createNewTask = new InlineKeyboardButton();
        createNewTask.setText(ButtonText.CREATE_NEW_TASK);
        createNewTask.setCallbackData(boardId + CommonText.CALLBACK_DELIMITER + CallbackText.CREATE_NEW_TASK);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(allBoardTasks);
        row.add(createNewTask);
        rows.add(row);
        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }
}
