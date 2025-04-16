package com.guarantee_respect.filesharing.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class FileEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String originalName;
    private String filePath;
    private String contentType;

    @CreationTimestamp
    private LocalDateTime uploadedAt;

    public FileEntity(String originalName, String filePath, String contentType) {
        this.originalName = originalName;
        this.filePath = filePath;
        this.contentType = contentType;
    }

    public FileEntity() {
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}