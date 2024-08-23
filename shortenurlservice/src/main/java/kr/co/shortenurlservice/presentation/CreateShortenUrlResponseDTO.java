package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.domain.ShortenUrl;

public class CreateShortenUrlResponseDTO {
    public String originalUrl;
    public String shortenUrlKey;

    public CreateShortenUrlResponseDTO() { }

    public CreateShortenUrlResponseDTO(ShortenUrl shortenUrl) {
        this.originalUrl = shortenUrl.getOriginalUrl();
        this.shortenUrlKey = shortenUrl.getShortenUrlKey();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }
}
