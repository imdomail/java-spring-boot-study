package kr.co.shortenurlservice.presentation;
import kr.co.shortenurlservice.domain.ShortenUrl;

public class ShortenUrlDTO {
    private String originalUrl;
    private String shortenUrlKey;
    private int redirectCount;

    public ShortenUrlDTO() { }
    public ShortenUrlDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public ShortenUrlDTO(String originalUrl, String shortenUrlKey, int redirectCount) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = redirectCount;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public static ShortenUrlDTO toDTO(ShortenUrl shortenUrl) {
        return new ShortenUrlDTO(
                shortenUrl.getOriginalUrl(),
                shortenUrl.getShortenUrlKey(),
                shortenUrl.getRedirectCount()
        );
    }
}
