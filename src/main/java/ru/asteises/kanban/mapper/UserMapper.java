package ru.asteises.kanban.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.model.entity.UserEntity;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "username", expression = "java(callbackQuery.getFrom().getUserName())")
    @Mapping(target = "chatId", source = "chatId")
    UserEntity toEntity(Long chatId, CallbackQuery callbackQuery);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "username", expression = "java(message.getChat().getUserName())")
    @Mapping(target = "chatId", source = "chatId")
    UserEntity toEntity(Long chatId, Message message);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "chatId", source = "chatId")
    UserEntity toEntity(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "chatId", source = "chatId")
    User toDto(UserEntity entity);
}
