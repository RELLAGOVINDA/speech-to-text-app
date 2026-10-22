package com.procucev.speechtotext.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TranscriptionRequest(
    @NotBlank @Size(max = 120) String title,
    @NotBlank String transcribedText,
    @NotBlank @Size(max = 40) String language
) {}
