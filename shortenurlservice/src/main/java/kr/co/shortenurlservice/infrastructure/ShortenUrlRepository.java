package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.EntityNotFoundException;
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
        if (found == null) {
            throw new EntityNotFoundException("단축 URL을 찾지 못했습니다.");
        }
        return found;
    }

    public ShortenUrl findByShortenUrlKeyAndIncreaseCount(String key) {
        ShortenUrl foundShortenUrl = findByShortenUrlKey(key);
        foundShortenUrl.increaseRedirectCount();
        return foundShortenUrl;
    }

}
