package ru.asteises.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.asteises.kanban.model.entity.BoardEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoardJpaRepository extends JpaRepository<BoardEntity, UUID> {

    @Query("""
            select e from BoardEntity as e
            left join fetch e.owner
            where e.deleted = false
            and e.id = :boardId
            """)
    Optional<BoardEntity> findByIdAndDeletedFalse(UUID boardId);
}
