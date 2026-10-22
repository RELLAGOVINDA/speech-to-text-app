package com.procucev.speechtotext.controller;

import com.procucev.speechtotext.dto.TranscriptionRequest;
import com.procucev.speechtotext.entity.Transcription;
import com.procucev.speechtotext.service.TranscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transcriptions")
//@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@CrossOrigin(origins = "*")
public class TranscriptionController {
    private final TranscriptionService service;

    public TranscriptionController(TranscriptionService service) { 
    	this.service = service; 
    }

    @GetMapping
    public List<Transcription> all() {
    	return service.findAll(); 
    }

    @GetMapping("/{id}")
    public Transcription one(@PathVariable Long id) {
    	return service.findById(id); 
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transcription create(@Valid @RequestBody TranscriptionRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public Transcription update(@PathVariable Long id, @Valid @RequestBody TranscriptionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
    	service.delete(id);
    }
}
