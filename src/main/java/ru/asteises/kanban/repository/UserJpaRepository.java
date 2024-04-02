package ru.asteises.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.asteises.kanban.model.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    @Query("""
            select e from UserEntity as e
            where e.deleted = false
            and e.chatId = :chatId
            """)
    Optional<UserEntity> findByChatIdAndDeletedFalse(Long chatId);
}
