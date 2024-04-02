package ru.asteises.kanban.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.asteises.kanban.model.entity.common.BaseFields;

import java.util.Objects;
import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity extends BaseFields {

    @Id
    private UUID id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity creator;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity executor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", dateModify=" + dateModify +
                ", userModify='" + userModify + '\'' +
                ", deleted=" + deleted +
                ", version=" + version +
                '}';
    }
}
