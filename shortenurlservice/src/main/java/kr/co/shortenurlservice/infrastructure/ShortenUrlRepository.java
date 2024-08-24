package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;


@Repository
public class ShortenUrlRepository {

    private HashMap<String, ShortenUrl> shortenUrls = new HashMap<>();

    public void add(ShortenUrl shortenUrl) {
        shortenUrls.put(shortenUrl.getShortenUrlKey(), shortenUrl);
    }

    public Collection<ShortenUrl> findAll() {
        return shortenUrls.values();
    }

    public ShortenUrl findByShortenUrlKey(String shortenUrlKey) {
        ShortenUrl found = shortenUrls.get(shortenUrlKey);
        return found;
    }

    public ShortenUrl findByShortenUrlKeyAndIncreaseCount(String shortenUrlKey) {
        ShortenUrl foundShortenUrl = findByShortenUrlKey(shortenUrlKey);
        if (foundShortenUrl == null) {
            return null;
        }
        foundShortenUrl.increaseRedirectCount();
        return foundShortenUrl;
    }

}
