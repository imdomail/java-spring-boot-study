package kr.co.shortenurlservice.presentation;

public class CreateShortenUrlRequestDTO {
    public String originalUrl;

    public CreateShortenUrlRequestDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
