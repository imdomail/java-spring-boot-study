package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.CreateShortenUrlResponseDTO;
import kr.co.shortenurlservice.presentation.ShortenUrlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class ShortenUrlService {

    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    ShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public CreateShortenUrlResponseDTO createShortenUrl(CreateShortenUrlRequestDTO request) {
        String shortenUrlKey = this.generateShortenUrlKey(request.originalUrl);
        ShortenUrl shortenUrl = new ShortenUrl(request.originalUrl, shortenUrlKey);
        shortenUrlRepository.add(shortenUrl);
        return new CreateShortenUrlResponseDTO(shortenUrl);
    }

    private String generateShortenUrlKey(String originalUrl) {
        String base58Characters = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        int keyLength = 8;
        Random random = new Random();
        StringBuilder shortenUrlKey = new StringBuilder();

        for (int i = 0; i < keyLength; i++) {
            int randomIdx = random.nextInt(base58Characters.length());
            char randomChar = base58Characters.charAt(randomIdx);
            shortenUrlKey.append(randomChar);
        }
        return shortenUrlKey.toString();
    }

    public ShortenUrlDTO findByShortenUrlKey(String shortenUrlKey) {
        return ShortenUrlDTO.toDTO(shortenUrlRepository.findByShortenUrlKey(shortenUrlKey));
    }

    public ShortenUrlDTO findByShortenUrlKeyAndIncreaseCount(String shortenUrlKey) {
        return ShortenUrlDTO.toDTO(shortenUrlRepository.findByShortenUrlKeyAndIncreaseCount(shortenUrlKey));
    }

    public Collection<ShortenUrlDTO> findAll() {
        return shortenUrlRepository.findAll().stream()
                .map((shortenUrl -> ShortenUrlDTO.toDTO(shortenUrl)))
                .toList();
    }

}
