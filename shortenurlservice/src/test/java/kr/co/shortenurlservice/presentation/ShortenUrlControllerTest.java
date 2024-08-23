package kr.co.shortenurlservice.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        CreateShortenUrlResponseDTO createResponse = shortenUrlController.createShortenUrl(request).getBody();

        String shortenUrlKey = createResponse.shortenUrlKey;

        ShortenUrlDTO shortenUrlDTO = shortenUrlController.getShortenUrlInformation(shortenUrlKey);
        assertEquals(shortenUrlDTO.getOriginalUrl(), originalUrl);
        assertEquals(shortenUrlDTO.getShortenUrlKey(), shortenUrlKey);
        assertTrue(shortenUrlDTO.getRedirectCount() >= 0);
    }

    @Test
    @DisplayName("동일한 요청에 새 단축 URL이 생성되어도 이전 단축 URL이 동작해야한 다. ")
    void sameUrlTest() {
        String originalUrl = "https://threejs.org/manual/#en/custom-buffergeometry";
        CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);

        CreateShortenUrlResponseDTO response1 = shortenUrlController.createShortenUrl(request).getBody();
        CreateShortenUrlResponseDTO response2 = shortenUrlController.createShortenUrl(request).getBody();
        ResponseEntity redirectResponse = shortenUrlController.redirectShortenUrl(response1.shortenUrlKey);

        assertTrue(redirectResponse.getStatusCode().isSameCodeAs(HttpStatus.MOVED_PERMANENTLY));
        assertEquals(redirectResponse.getHeaders().getLocation().toString(), originalUrl);
    }

}