package ru.asteises.kanban.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.asteises.kanban.mapper.TaskMapper;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.model.dto.Task;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.model.entity.TaskEntity;
import ru.asteises.kanban.repository.TaskJpaRepository;
import ru.asteises.kanban.service.BoardService;
import ru.asteises.kanban.service.TaskService;
import ru.asteises.kanban.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskJpaRepository taskJpaRepository;
    private final UserService userService;
    private final BoardService boardService;

    @Override
    public Task createTask(Long userChatId, String taskName) {
        User creator = userService.getUserByChatId(userChatId);

        TaskEntity entity = TaskMapper.INSTANCE.toEntity(creator, taskName);
        taskJpaRepository.save(entity);
        log.info("new TaskEntity create: {}", entity);
        return TaskMapper.INSTANCE.toDto(entity);
    }

    @Override
    public Task createTask(Long userChatId, String taskName, String boardId) {
        User creator = userService.getUserByChatId(userChatId);
        log.info("find user for create task: {}", creator);
        Board board = boardService.getBoardById(boardId);
        log.info("find board to create task: {}", board);
        TaskEntity entity = TaskMapper.INSTANCE.toEntity(creator, board, taskName);
        taskJpaRepository.save(entity);
        log.info("new TaskEntity create: {}", entity);
        return TaskMapper.INSTANCE.toDto(entity);
    }
}