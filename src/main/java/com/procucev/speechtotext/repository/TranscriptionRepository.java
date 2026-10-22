package com.procucev.speechtotext.repository;

import com.procucev.speechtotext.entity.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptionRepository extends JpaRepository<Transcription, Long> {
}
