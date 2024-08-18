package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ShortenUrlController {

    private ShortenUrlService shortenUrlService;

    @Autowired
    ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @RequestMapping(value = "/shorten-url", method = RequestMethod.POST)
    public CreateShortenUrlResponseDTO createShortenUrl(@RequestBody CreateShortenUrlRequestDTO request) {
        ShortenUrl shortenUrl = shortenUrlService.createShortenUrl(request.originalUrl);
        return CreateShortenUrlResponseDTO.toDTO(shortenUrl);
    }

    @RequestMapping(value = "/shorten-url/{shortenUrlKey}", method = RequestMethod.GET)
    public ShortenUrl getShortenUrlInformation(@PathVariable String shortenUrlKey) {
        return shortenUrlService.findByShortenUrlKey(shortenUrlKey);
    }

    @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity redirectShortenUrl(@PathVariable String shortenUrlKey) {
        ShortenUrl shortenUrl = shortenUrlService.findByShortenUrlKeyAndIncreaseCount(shortenUrlKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(shortenUrl.getOriginalUrl()));

        return new ResponseEntity(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
