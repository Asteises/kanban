package ru.asteises.kanban.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SendMessageMapper {

    SendMessageMapper INSTANCE = Mappers.getMapper(SendMessageMapper.class);

    SendMessage toSendMessage(Long chatId, String text);
}
