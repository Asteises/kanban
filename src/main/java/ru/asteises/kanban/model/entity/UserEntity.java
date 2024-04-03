package ru.asteises.kanban.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.asteises.kanban.model.entity.common.BaseFields;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseFields {

    @Id
    private UUID id;
    private String username;
    private Long chatId;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "users_boards",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"))
//    private Set<BoardEntity> boards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", dateModify=" + dateModify +
                ", userModify='" + userModify + '\'' +
                ", deleted=" + deleted +
                ", version=" + version +
                '}';
    }
}
