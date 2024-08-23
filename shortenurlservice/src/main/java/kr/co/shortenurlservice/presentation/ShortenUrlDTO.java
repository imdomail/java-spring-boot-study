package kr.co.shortenurlservice.presentation;
import kr.co.shortenurlservice.domain.ShortenUrl;

public class ShortenUrlDTO {
    private String originalUrl;
    private String shortenUrlKey;
    private Long redirectCount;

    public ShortenUrlDTO() { }
    public ShortenUrlDTO(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public ShortenUrlDTO(String originalUrl, String shortenUrlKey, Long redirectCount) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0L;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public Long getRedirectCount() { return redirectCount; };

    public static ShortenUrlDTO toDTO(ShortenUrl shortenUrl) {
        return new ShortenUrlDTO(
                shortenUrl.getOriginalUrl(),
                shortenUrl.getShortenUrlKey(),
                shortenUrl.getRedirectCount()
        );
    }
}
