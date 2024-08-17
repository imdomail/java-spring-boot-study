package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
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

        ShortenUrl shortenUrl = shortenUrlService.createShortenUrl(originalUrl);
        Collection<ShortenUrl> list = shortenUrlService.findAll();

        assertTrue(list.contains(shortenUrl));
        assertEquals(shortenUrl.getOriginalUrl(), originalUrl);
    }
}