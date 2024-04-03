package ru.asteises.kanban.service;

import ru.asteises.kanban.model.dto.Board;

import java.util.List;

public interface BoardService {

    Board createBoard(Long userChatId, String boardName);

    Board getBoardById(String boardId);

    List<Board> getAllBoardsByUserId(Long userId);
}
