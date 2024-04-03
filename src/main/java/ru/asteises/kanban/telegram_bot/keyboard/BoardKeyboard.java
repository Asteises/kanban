package ru.asteises.kanban.telegram_bot.keyboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.service.BoardService;
import ru.asteises.kanban.utils.AppText;
import ru.asteises.kanban.utils.ButtonText;
import ru.asteises.kanban.utils.CallbackText;
import ru.asteises.kanban.utils.CommonText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class BoardKeyboard {

    private final BoardService boardService;

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

    public SendMessage allUserBoards(long chatId) {
        List<Board> boards = boardService.getAllBoardsByUserId(chatId);
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("You boards:");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (var board : boards) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton boardButton = new InlineKeyboardButton();
            boardButton.setText(board.getName());
            boardButton.setCallbackData(board.getId() + CommonText.CALLBACK_DELIMITER + CallbackText.GET_BOARD);
            row.add(boardButton);
            rows.add(row);
        }
        keyboardMarkup.setKeyboard(rows);
        message.setReplyMarkup(keyboardMarkup);
        return message;
    }

}
