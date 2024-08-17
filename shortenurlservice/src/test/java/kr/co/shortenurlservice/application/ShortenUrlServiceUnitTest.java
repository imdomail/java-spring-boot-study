package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ShortenUrlServiceUnitTest {

    @InjectMocks
    private ShortenUrlService shortenUrlService;

    @Test
    @DisplayName("createShortenUrl은 key를 생성하고 ShortenUrl 객체를 리턴해야 한다.")
    void createShortenUrlTest() {
        String originalUrl = "https://www.banul.co.kr/shop/shopbrand.html?type=Y&xcode=114";

        ShortenUrl shortenUrl = shortenUrlService.createShortenUrl(originalUrl);

        assertEquals(shortenUrl.getOriginalUrl(), originalUrl);
        assertEquals(shortenUrl.getShortenUrlKey().length(), 8);
    }
}