package foo.study.url.controller;

import foo.study.url.dto.UrlDto;
import foo.study.url.dto.UrlDto.Request;
import foo.study.url.dto.UrlDto.Response;
import foo.study.url.dto.UrlDto.Response.FindList;
import foo.study.url.dto.UrlDto.Response.FindOne;
import foo.study.url.dto.ValidationSequence;
import foo.study.url.service.UrlService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/urls")
    public Response.FindList<String> findAll() {
        return FindList.<String>builder()
            .list(urlService.findAll()).build();
    }

    @GetMapping("/forward")
    public void redirect(@RequestParam(name = "shortenUrl") String shortenUrl,
        HttpServletResponse response) throws IOException {
        String originalUrl = urlService.findOriginalUrlByShortedUrl(shortenUrl);
        log.info("originalUrl {}", originalUrl);
        response.sendRedirect(originalUrl);
    }
}