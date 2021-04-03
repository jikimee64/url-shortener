package foo.study.url.controller;

import foo.study.url.dto.UrlDto;
import foo.study.url.dto.UrlDto.Request;
import foo.study.url.dto.UrlDto.Response;
import foo.study.url.dto.UrlDto.Response.FindList;
import foo.study.url.dto.UrlDto.Response.FindOne;
import foo.study.url.dto.UrlDto.Response.Select;
import foo.study.url.dto.ValidationSequence;
import foo.study.url.service.UrlService;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/save")
    public Response.FindOne<String> save(
        @Validated(ValidationSequence.class) @RequestBody UrlDto.Request.Save dto) {
        String shortenUrl = urlService.save(Request.toEntity(dto));
        log.info("shortenUrl {}", shortenUrl);
        return FindOne.<String>builder()
            .data(shortenUrl)
            .build();
    }

    @GetMapping("/select")
    public Response.FindList<Select> findAll() {
        return FindList.<Select>builder()
            .list(urlService.findAll()).build();
    }

    @GetMapping("/forward")
    public ResponseEntity<?> redirect(@RequestParam(name = "shortenUrl") String shortenUrl) throws URISyntaxException {
        String originalUrl = urlService.findOriginalUrlByShortedUrl(shortenUrl);
        URI redirectUri = new URI(originalUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}