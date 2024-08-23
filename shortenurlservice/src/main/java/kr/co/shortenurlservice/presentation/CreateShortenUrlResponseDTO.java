package kr.co.shortenurlservice.presentation;

public class CreateShortenUrlResponseDTO {
    public String shortenUrlKey;

    public CreateShortenUrlResponseDTO(ShortenUrlDTO shortenUrlDTO) {
        this.shortenUrlKey = shortenUrlDTO.getShortenUrlKey();
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }
}
