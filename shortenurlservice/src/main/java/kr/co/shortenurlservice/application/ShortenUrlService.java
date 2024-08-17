package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShortenUrlService {

    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    ShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrl createShortenUrl(String originalUrl) {
        String shortenUrlKey = this.generateKey(originalUrl);
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortenUrlKey);
        shortenUrlRepository.add(shortenUrl);
        return shortenUrl;
    }

    private String generateKey(String originalUrl) {
        String base58Characters = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        int keyLength = 8;
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            int randomIdx = (int) Math.floor(Math.random() * base58Characters.length());
            char randomChar = base58Characters.charAt(randomIdx);
            key.append(randomChar);
        }
        return key.toString();
    }

    public ShortenUrl findByShortenUrlKey(String key) {
        return shortenUrlRepository.findByShortenUrlKey(key);
    }

    public Collection<ShortenUrl> findAll() {
        return shortenUrlRepository.findAll();
    }

}
