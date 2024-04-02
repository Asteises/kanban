package ru.asteises.kanban.service;

import ru.asteises.kanban.model.dto.Board;

public interface BoardService {

    Board createBoard(Long userChatId, String boardName);
}
