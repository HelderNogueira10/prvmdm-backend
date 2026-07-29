package com.privguard.mdm.server.base;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setCreatedAt(LocalDateTime _createdAt) { this.createdAt = _createdAt; }
    public void setUpdatedAt(LocalDateTime _updatedAt) { this.updatedAt = _updatedAt; }

    public long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    @PrePersist
    protected void onCreate() {
        
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {

        updatedAt = LocalDateTime.now();
    }
}
