package ru.asteises.kanban.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.kanban.mapper.BoardMapper;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.model.entity.BoardEntity;
import ru.asteises.kanban.repository.BoardJpaRepository;
import ru.asteises.kanban.service.BoardService;
import ru.asteises.kanban.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final UserService userService;

    @Override
    public Board createBoard(Long userChatId, String boardName) {
        User user = userService.getUserByChatId(userChatId);
        BoardEntity entity = BoardMapper.INSTANCE.toEntity(user, boardName);
        boardJpaRepository.save(entity);
        log.info("new board create: {}", entity);
        return BoardMapper.INSTANCE.toDto(entity);
    }
}