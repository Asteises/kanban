package ru.asteises.kanban.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.model.dto.Task;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.model.entity.TaskEntity;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class},
        uses = {UserMapper.class, BoardMapper.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "creator", expression = "java(UserMapper.INSTANCE.toEntity(user))")
    @Mapping(target = "executor", ignore = true)
    TaskEntity toEntity(User user, String name);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "board", expression = "java(BoardMapper.INSTANCE.toEntity(board))")
    @Mapping(target = "creator", expression = "java(UserMapper.INSTANCE.toEntity(user))")
    @Mapping(target = "executor", ignore = true)
    TaskEntity toEntity(User user, Board board, String name);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "board", expression = "java(BoardMapper.INSTANCE.toDto(entity.getBoard()))")
    @Mapping(target = "creator", expression = "java(UserMapper.INSTANCE.toDto(entity.getCreator()))")
    @Mapping(target = "executor", ignore = true)
    Task toDto(TaskEntity entity);
}
