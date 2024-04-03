package ru.asteises.kanban.telegram_bot.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.model.dto.Task;
import ru.asteises.kanban.utils.CallbackText;
import ru.asteises.kanban.utils.CommonText;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskKeyboard {

    public static SendMessage setTaskNameButton(long chatId, String taskId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("1");

        InlineKeyboardButton taskNameButton = new InlineKeyboardButton();
        taskNameButton.setText("Enter task title");
        taskNameButton.setCallbackData(taskId + CommonText.CALLBACK_DELIMITER + CallbackText.SET_TASK_NAME);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(taskNameButton);
        rows.add(row);
        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    public static  SendMessage mainTaskKeyboard(long chatId, Task task) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(task.getName());

        String taskId = task.getId().toString();

        InlineKeyboardButton setDescriptionButton = new InlineKeyboardButton();
        setDescriptionButton.setText("Добавить описание");
        setDescriptionButton.setCallbackData(taskId + CommonText.CALLBACK_DELIMITER + CallbackText.SET_TASK_DESCRIPTION);

        InlineKeyboardButton setDoneButton = new InlineKeyboardButton();
        setDoneButton.setText("Завершить задачу");
        setDoneButton.setCallbackData(taskId + CommonText.CALLBACK_DELIMITER + CallbackText.SET_TASK_DONE);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row1.add(setDescriptionButton);
        row2.add(setDoneButton);
        rows.add(row1);
        rows.add(row2);
        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    public static SendMessage getAllUserTasks(Long chatId, List<Task> tasks) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("You actual tasks:");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (var task : tasks) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton taskButton = new InlineKeyboardButton();
            taskButton.setText(task.getName());
            taskButton.setCallbackData(task.getId() + CommonText.CALLBACK_DELIMITER + CallbackText.GET_TASK);
            row.add(taskButton);
            rows.add(row);
        }
        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }
}
