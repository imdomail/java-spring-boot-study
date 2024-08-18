package kr.co.shortenurlservice.domain;

public class ShortenUrl {
    private String originalUrl;
    private String shortenUrlKey;
    private int redirectCount;

    public ShortenUrl() {}

    public ShortenUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public ShortenUrl(String originalUrl, String shortenUrlKey) {
        this.originalUrl = originalUrl;
        this.shortenUrlKey = shortenUrlKey;
        this.redirectCount = 0;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortenUrlKey() {
        return shortenUrlKey;
    }

    public int getRedirectCount() {
        return redirectCount;
    }

    public void increaseRedirectCount () {
        redirectCount = redirectCount + 1;
    }

}
