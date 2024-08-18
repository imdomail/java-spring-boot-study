package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.ShortenUrlDTO;
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

    public ShortenUrlDTO createShortenUrl(CreateShortenUrlRequestDTO request) {
        String shortenUrlKey = this.generateKey(request.originalUrl);
        ShortenUrl shortenUrl = new ShortenUrl(request.originalUrl, shortenUrlKey);
        shortenUrlRepository.add(shortenUrl);
        return ShortenUrlDTO.toDTO(shortenUrl);
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

    public ShortenUrlDTO findByShortenUrlKey(String key) {
        return ShortenUrlDTO.toDTO(shortenUrlRepository.findByShortenUrlKey(key));
    }

    public ShortenUrlDTO findByShortenUrlKeyAndIncreaseCount(String key) {
        return ShortenUrlDTO.toDTO(shortenUrlRepository.findByShortenUrlKeyAndIncreaseCount(key));
    }

    public Collection<ShortenUrlDTO> findAll() {
        return shortenUrlRepository.findAll().stream()
                .map((shortenUrl -> ShortenUrlDTO.toDTO(shortenUrl)))
                .toList();
    }

}
