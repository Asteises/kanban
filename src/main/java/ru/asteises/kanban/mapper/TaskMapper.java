package ru.asteises.kanban.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.model.dto.Task;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.model.entity.TaskEntity;

import java.util.List;
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

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "board", expression = "java(setBoard(entity))")
    @Mapping(target = "creator", source = "creator")
    @Mapping(target = "executor", ignore = true)
    Task toDto(TaskEntity entity, User creator);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "board", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "executor", ignore = true)
    Task toSimpleDto(TaskEntity entity);

    @Named("setBoard")
    default Board setBoard(TaskEntity entity) {
        if (entity.getBoard() != null) {
            return BoardMapper.INSTANCE.toDto(entity.getBoard());
        }
        return null;
    }
}
