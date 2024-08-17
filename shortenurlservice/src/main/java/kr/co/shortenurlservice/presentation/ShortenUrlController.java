package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenUrlController {

    private ShortenUrlService shortenUrlService;

    @Autowired
    ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @RequestMapping(value = "/shorten-url", method = RequestMethod.POST)
    public ShortenUrl createShortenUrl() {
        // 단축 url 생성하기
        System.out.println("CreateShortenUrl");
        return new ShortenUrl();
    }

    @RequestMapping(value = "/shorten-url/{key}", method = RequestMethod.GET)
    public ShortenUrl getShortenUrlInformation() {
        // 정보 조회
        System.out.println("GetShortenUrlInformation");
        return new ShortenUrl();
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public ResponseEntity redirectShortenUrl() {
        // 리다이렉트
        System.out.println("Redirect");
        return new ResponseEntity(HttpStatus.PERMANENT_REDIRECT);
    }
}
