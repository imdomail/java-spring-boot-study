package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.ShortenUrlDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ShortenUrlServiceUnitTest {

    @Mock
    private ShortenUrlRepository shortenUrlRepository;

    @InjectMocks
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("createShortenUrl은 key를 생성하고 ShortenUrl 객체를 리턴해야 한다.")
    void createShortenUrlTest() {
        String originalUrl = "https://www.banul.co.kr/shop/shopbrand.html?type=Y&xcode=114";
        CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);

        ShortenUrlDTO shortenUrlDTO = shortenUrlService.createShortenUrl(request);

        assertEquals(shortenUrlDTO.getOriginalUrl(), originalUrl);
        assertEquals(shortenUrlDTO.getShortenUrlKey().length(), 8);
    }
}