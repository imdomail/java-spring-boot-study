package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.stereotype.Service;

@Service
public class ShortenUrlService {

    public ShortenUrl createShortenUrl(String originalUrl) {
        String shortenUrlKey = this.generateKey(originalUrl);
        // TODO: add to list
        return new ShortenUrl(originalUrl, shortenUrlKey);
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

}
