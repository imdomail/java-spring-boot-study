package kr.co.shortenurlservice.presentation;

import jakarta.validation.constraints.NotBlank;

public class CreateShortenUrlRequestDTO {
    @NotBlank
    public String originalUrl;

    public CreateShortenUrlRequestDTO() { }

    public CreateShortenUrlRequestDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
