package com.procucev.speechtotext.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transcriptions")
public class Transcription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Column(name = "transcribed_text", nullable = false, columnDefinition = "LONGTEXT")
    private String transcribedText;

    @Column(name = "language", nullable = false, length = 40)
    private String language;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
    	updatedAt = LocalDateTime.now();
    }

    public Long getId() { 
    	return id; 
    }
    public String getTitle() { 
    	return title; 
    }
    public void setTitle(String title) {
    	this.title = title;
    }
    public String getTranscribedText() {
    	return transcribedText;
    }
    public void setTranscribedText(String transcribedText) {
    	this.transcribedText = transcribedText;
    }
    public String getLanguage() {
    	return language; 
    }
    public void setLanguage(String language) {
    	this.language = language; 
    }
    public LocalDateTime getCreatedAt() {
    	return createdAt; 
    }
    public LocalDateTime getUpdatedAt() {
    	return updatedAt; 
    }
}
