package kr.co.shortenurlservice.domain;

public class ShortenUrl {
    private String originalUrl;
    private String shortenUrlKey;
    private Number redirectCount;

    public ShortenUrl() {}

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public Number getRedirectCount() {
        return redirectCount;
    }

}
