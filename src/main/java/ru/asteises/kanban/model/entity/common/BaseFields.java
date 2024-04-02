package ru.asteises.kanban.model.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseFields {

    /**
     * дата создания записи
     */
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime createDate;

    /**
     * дата создания записи
     */
    @Column(name = "create_by")
    @CreatedBy
    protected String createdBy;

    /**
     * дата изменения записи
     */
    @Column(name = "date_modify")
    @LastModifiedDate
    protected LocalDateTime dateModify;

    /**
     * пользователь, изменивший запись
     */
    @Column(name = "user_modify")
    @LastModifiedBy
    protected String userModify;

    /**
     * признак того, что запись удалена
     */
    @Column(name = "deleted")
    protected boolean deleted;

    /**
     * версия записи
     */
    @Column(name = "version")
    @Version
    protected int version;

}
