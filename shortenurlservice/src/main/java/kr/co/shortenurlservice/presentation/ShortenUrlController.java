package kr.co.shortenurlservice.presentation;

import jakarta.validation.Valid;
import kr.co.shortenurlservice.application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ShortenUrlController {

    private ShortenUrlService shortenUrlService;

    @Autowired
    ShortenUrlController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    @RequestMapping(value = "/shorten-url", method = RequestMethod.POST)
    public ResponseEntity<CreateShortenUrlResponseDTO> createShortenUrl(@Valid @RequestBody CreateShortenUrlRequestDTO request) {
        CreateShortenUrlResponseDTO response = shortenUrlService.createShortenUrl(request);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/shorten-url/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity<ShortenUrlDTO> getShortenUrlInformation(@PathVariable String shortenUrlKey) {
        ShortenUrlDTO response = shortenUrlService.findByShortenUrlKey(shortenUrlKey);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
    public ResponseEntity redirectShortenUrl(@PathVariable String shortenUrlKey) throws URISyntaxException {
        ShortenUrlDTO shortenUrlDTO = shortenUrlService.findByShortenUrlKeyAndIncreaseCount(shortenUrlKey);
        String originalUrl = shortenUrlDTO.getOriginalUrl();

        URI redirectUri = new URI(originalUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(redirectUri);

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
