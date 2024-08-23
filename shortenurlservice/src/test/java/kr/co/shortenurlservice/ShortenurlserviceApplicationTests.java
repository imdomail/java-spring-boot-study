package kr.co.shortenurlservice;

import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.CreateShortenUrlResponseDTO;
import kr.co.shortenurlservice.presentation.ShortenUrlController;
import kr.co.shortenurlservice.presentation.ShortenUrlDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShortenurlserviceApplicationTests {

	@Autowired
	ShortenUrlController shortenUrlController;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("단축 URL 생성 후 정보 조회할 수 있다.")
	void getShortenUrlInformationTest() {
		String originalUrl = "https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=339684077&start=wz&ptid=9&utm_source=aladin&utm_medium=wizard&utm_campaign=choice&utm_content=welcome";
		CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);
		CreateShortenUrlResponseDTO createResponse = shortenUrlController.createShortenUrl(request).getBody();

		String shortenUrlKey = createResponse.shortenUrlKey;

		ShortenUrlDTO shortenUrlDTO = shortenUrlController.getShortenUrlInformation(shortenUrlKey).getBody();
		assertEquals(shortenUrlDTO.getOriginalUrl(), originalUrl);
		assertEquals(shortenUrlDTO.getShortenUrlKey(), shortenUrlKey);
		assertTrue(shortenUrlDTO.getRedirectCount() >= 0);
	}

	@Test
	@DisplayName("동일한 요청에 새 단축 URL이 생성되어도 이전 단축 URL이 동작해야한다. ")
	void sameUrlTest() throws URISyntaxException {
		String originalUrl = "https://threejs.org/manual/#en/custom-buffergeometry";
		CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);

		CreateShortenUrlResponseDTO response1 = shortenUrlController.createShortenUrl(request).getBody();
		CreateShortenUrlResponseDTO response2 = shortenUrlController.createShortenUrl(request).getBody();
		ResponseEntity redirectResponse = shortenUrlController.redirectShortenUrl(response1.shortenUrlKey);

		assertTrue(redirectResponse.getStatusCode().isSameCodeAs(HttpStatus.MOVED_PERMANENTLY));
		assertEquals(redirectResponse.getHeaders().getLocation().toString(), originalUrl);
	}

	@Test
	@DisplayName("리다이렉트 된 후 redirectCount 값이 증가해야 한다.")
	void redirectCountTest() throws URISyntaxException {
		String originalUrl = "https://www.aladin.co.kr/events/wevent.aspx?EventId=271752&start=pinTab";
		CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO(originalUrl);
		CreateShortenUrlResponseDTO response = shortenUrlController.createShortenUrl(request).getBody();
		String shortenUrlKey = response.getShortenUrlKey();

		shortenUrlController.redirectShortenUrl(shortenUrlKey);
		ShortenUrlDTO shortenUrlInformation = shortenUrlController.getShortenUrlInformation(shortenUrlKey).getBody();

		assertTrue(shortenUrlInformation.getRedirectCount() >= 1);
	}
}
