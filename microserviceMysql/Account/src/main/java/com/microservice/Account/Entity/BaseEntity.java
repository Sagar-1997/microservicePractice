package com.microservice.Account.Entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.microservice.Account.audit.AuditAwareImpl;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass // a class whose mapping information is applied to the entities that inherit
                  // from it
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate // populate current at in field
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy // automatically populate a field with the user or system that created an entity
    @Column(updatable = false) // not include field in update statement.
    private String createdBy;

    @LastModifiedDate // automatically populate last modifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy // the principal that recently modified the entity containing the field.
    @Column(insertable = false) // not include field in insert statement.
    private String updatedBy;
}
