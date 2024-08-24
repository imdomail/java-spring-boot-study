package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.LackOfShortenUrlKeyException;
import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.CreateShortenUrlRequestDTO;
import kr.co.shortenurlservice.presentation.CreateShortenUrlResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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

        CreateShortenUrlResponseDTO shortenUrlDTO = shortenUrlService.createShortenUrl(request);

        assertEquals(shortenUrlDTO.getOriginalUrl(), originalUrl);
        assertEquals(shortenUrlDTO.getShortenUrlKey().length(), 8);
    }

    @Test
    @DisplayName("단축 URL이 계속 중복되면 LackOfShortenUrlKeyException이 발생해야 한다.")
    void throwLackOfShortenUrlKeyExceptionTest() {
        CreateShortenUrlRequestDTO request = new CreateShortenUrlRequestDTO();

        when(shortenUrlRepository.findByShortenUrlKey(any())).thenReturn(new ShortenUrl());

        assertThrows(LackOfShortenUrlKeyException.class, () -> {
            shortenUrlService.createShortenUrl(request);
        });
    }
}