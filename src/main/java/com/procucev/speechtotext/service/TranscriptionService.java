package com.procucev.speechtotext.service;

import com.procucev.speechtotext.dto.TranscriptionRequest;
import com.procucev.speechtotext.entity.Transcription;
import com.procucev.speechtotext.repository.TranscriptionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TranscriptionService {
    private final TranscriptionRepository repository;

    public TranscriptionService(TranscriptionRepository repository) {
        this.repository = repository;
    }

    public List<Transcription> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Transcription findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new TranscriptionNotFoundException(id));
    }

    public Transcription create(TranscriptionRequest request) {
        Transcription t = new Transcription();
        apply(t, request);
        return repository.save(t);
    }

    public Transcription update(Long id, TranscriptionRequest request) {
        Transcription t = findById(id);
        apply(t, request);
        return repository.save(t);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    private void apply(Transcription t, TranscriptionRequest request) {
        t.setTitle(request.title().trim());
        t.setTranscribedText(request.transcribedText().trim());
        t.setLanguage(request.language().trim());
    }
}
