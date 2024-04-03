package ru.asteises.kanban.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.asteises.kanban.mapper.UserMapper;
import ru.asteises.kanban.model.dto.User;
import ru.asteises.kanban.model.entity.UserEntity;
import ru.asteises.kanban.repository.UserJpaRepository;
import ru.asteises.kanban.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    @Transactional
    @Override
    public User createUser(Long chatId, Message message) {
        UserEntity entity = UserMapper.INSTANCE.toEntity(chatId, message);
        userJpaRepository.save(entity);
        log.info("new UserEntity created: {}", entity);
        return UserMapper.INSTANCE.toDto(entity);
    }

    @Override
    public User getUserByChatId(Long chatId) {
        UserEntity entity = userJpaRepository.findByChatIdAndDeletedFalse(chatId).orElseThrow(() -> {
            log.error("UserEntity with chatId: {} not found", chatId);
            throw new RuntimeException(String.format("UserEntity with chatId: %s not found!", chatId));
        });
        return UserMapper.INSTANCE.toDto(entity);
    }
}
