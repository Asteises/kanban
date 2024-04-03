package ru.asteises.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.asteises.kanban.model.entity.TaskEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {

    @Query("""
            select e from TaskEntity as e
            left join fetch e.board
            where e.creator.chatId = :chatId
            and e.deleted = false
            """)
    List<TaskEntity> findAllByCreatorChatIdAndDeletedFalse(long chatId);

    @Query("""
            select e from TaskEntity as e
            left join e.board
            where e.id = :id
            and e.deleted = false
            """)
    Optional<TaskEntity> findByIdAndDeletedFalse(UUID id);
}
