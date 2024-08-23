package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.EntityNotFoundException;
import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.CreateShortenUrlResponseDTO;
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

        CreateShortenUrlResponseDTO shortenUrlDTO = shortenUrlService.createShortenUrl(request);
        assertEquals(shortenUrlDTO.getOriginalUrl(), originalUrl);

        Collection<ShortenUrlDTO> list = shortenUrlService.findAll();

        ShortenUrlDTO found = list.stream().filter(savedShortenUrl -> {
            return savedShortenUrl.getShortenUrlKey().equals(shortenUrlDTO.getShortenUrlKey())
                    && savedShortenUrl.getOriginalUrl().equals(shortenUrlDTO.getOriginalUrl());
        }).toList().get(0);
        assertNotNull(found);
    }

    @Test
    @DisplayName("존재하지 않는 shortenUrlKey로 조회하면 EntityNotFoundException이 발생해야 한다.")
    void findShortenUrlNotExistTest() {
        String notExistShortenUrlKey = "XX";

        assertThrows(EntityNotFoundException.class, () -> {
            shortenUrlService.findByShortenUrlKey(notExistShortenUrlKey);
        });
    }

    @Test
    @DisplayName("동일한 요청을 보내도 새로운 단축URL이 생성되어야 한다.")
    void sameUrlTest() {
        String originalUrl = "https://threejs.org/manual/#en/custom-buffergeometry";
        CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);

        CreateShortenUrlResponseDTO shortenUrlDTO1 = shortenUrlService.createShortenUrl(request);
        CreateShortenUrlResponseDTO shortenUrlDTO2 = shortenUrlService.createShortenUrl(request);

        assertNotEquals(shortenUrlDTO1.getShortenUrlKey(), shortenUrlDTO2.getShortenUrlKey());
    }
}