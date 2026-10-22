package com.procucev.speechtotext.controller;

import com.procucev.speechtotext.service.TranscriptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(TranscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFound(TranscriptionNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> validation(MethodArgumentNotValidException ex) {
        return Map.of("error", "Title, transcription text, and language are required. Title/language must be within the allowed length.");
    }
}
