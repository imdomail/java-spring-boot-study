package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.domain.ShortenUrl;

public class CreateShortenUrlResponseDTO {
    public String shortenUrlKey;

    public CreateShortenUrlResponseDTO(String shortenUrlKey) {
        this.shortenUrlKey = shortenUrlKey;
    }

    public static CreateShortenUrlResponseDTO toDTO(ShortenUrl shortenUrl) {
        return new CreateShortenUrlResponseDTO(shortenUrl.getShortenUrlKey());
    }
}
