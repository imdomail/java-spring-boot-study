package kr.co.shortenurlservice.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShortenUrlControllerTest {

    @Autowired
    ShortenUrlController shortenUrlController;

    @Test
    @DisplayName("단축 URL 생성 후 정보 조회할 수 있다.")
    void getShortenUrlInformationTest() {
        String originalUrl = "https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=339684077&start=wz&ptid=9&utm_source=aladin&utm_medium=wizard&utm_campaign=choice&utm_content=welcome";
        CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);
        CreateShortenUrlResponseDTO createResponse = shortenUrlController.createShortenUrl(request);

        String shortenUrlKey = createResponse.shortenUrlKey;

        ShortenUrlDTO shortenUrlDTO = shortenUrlController.getShortenUrlInformation(shortenUrlKey);
        assertEquals(shortenUrlDTO.getOriginalUrl(), originalUrl);
        assertEquals(shortenUrlDTO.getShortenUrlKey(), shortenUrlKey);
        assertTrue(shortenUrlDTO.getRedirectCount() >= 0);
    }

}