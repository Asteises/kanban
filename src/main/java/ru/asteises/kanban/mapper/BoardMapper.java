package ru.asteises.kanban.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.asteises.kanban.model.dto.Board;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.model.entity.BoardEntity;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class, UserMapper.class},
        uses = {UserMapper.class})
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "name", source = "boardName")
    @Mapping(target = "owner", expression = "java(UserMapper.INSTANCE.toEntity(user))")
    BoardEntity toEntity(User user, String boardName);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "owner", expression = "java(UserMapper.INSTANCE.toDto(entity.getOwner()))")
    Board toDto(BoardEntity entity);
}