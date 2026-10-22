package com.procucev.speechtotext.service;

public class TranscriptionNotFoundException extends RuntimeException {
    public TranscriptionNotFoundException(Long id) {
        super("Transcription with id " + id + " was not found");
    }
}
