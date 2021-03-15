package foo.study.url.controller;

import foo.study.url.dto.UrlDto;
import foo.study.url.dto.UrlDto.Response;
import foo.study.url.dto.UrlDto.Response.FindList;
import foo.study.url.dto.UrlDto.Response.FindOne;
import foo.study.url.dto.ValidationSequence;
import foo.study.url.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/save")
    public Response.FindOne<String> save( @Validated(ValidationSequence.class) @RequestBody UrlDto.Request.Save dto){
        return FindOne.<String>builder()
            .data(
                urlService.save(dto.getUrl()
            )
        ).build();
    }

    @GetMapping("/urls")
    public Response.FindList<String> findAll(){
        return FindList.<String>builder()
            .list(
                urlService.findAll()
            ).build();
    }
}
