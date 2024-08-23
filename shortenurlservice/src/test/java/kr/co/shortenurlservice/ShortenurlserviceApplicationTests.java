package kr.co.shortenurlservice;

import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.CreateShortenUrlResponseDTO;
import kr.co.shortenurlservice.presentation.ShortenUrlController;
import kr.co.shortenurlservice.presentation.ShortenUrlDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShortenurlserviceApplicationTests {

	@Autowired
	ShortenUrlController shortenUrlController;

	@Test
	void contextLoads() {
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
