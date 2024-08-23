package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.EntityNotFoundException;
import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.ShortenUrlDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortenUrlServiceTest {

    @Autowired
    ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("CreateShortenUrl은 단축 key를 생성하고 저장한다.")
    void createShortenUrlTest() {
        String originalUrl = "https://www.banul.co.kr/shop/shopbrand.html?type=Y&xcode=114";
        CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);

        ShortenUrlDTO shortenUrlDTO = shortenUrlService.createShortenUrl(request);
        assertEquals(shortenUrlDTO.getOriginalUrl(), originalUrl);

        Collection<ShortenUrlDTO> list = shortenUrlService.findAll();

        ShortenUrlDTO found = list.stream().filter(savedShortenUrl -> {
            return savedShortenUrl.getShortenUrlKey().equals(shortenUrlDTO.getShortenUrlKey())
                    && savedShortenUrl.getOriginalUrl().equals(shortenUrlDTO.getOriginalUrl());
        }).toList().get(0);
        assertTrue(found != null);
    }

    @Test
    @DisplayName("존재하지 않는 shortenUrlKey로 조회하면 EntityNotFoundException이 발생해야 한다.")
    void findShortenUrlNotExistTest() {
        String notExistShortenUrlKey = "XX";

        assertThrows(EntityNotFoundException.class, () -> {
            shortenUrlService.findByShortenUrlKey(notExistShortenUrlKey);
        });

    }
}