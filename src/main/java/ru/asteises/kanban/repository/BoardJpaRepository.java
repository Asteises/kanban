package ru.asteises.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asteises.kanban.model.entity.BoardEntity;

import java.util.UUID;

@Repository
public interface BoardJpaRepository extends JpaRepository<BoardEntity, UUID> {

}
