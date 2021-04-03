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
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/save")
    public Response.FindOne<String> save( @Validated(ValidationSequence.class) @RequestBody UrlDto.Request.Save dto){
        String shortenUrl = urlService.save(Request.toEntity(dto));
        return FindOne.<String>builder()
            .data()
        ).build();
    }

    @GetMapping("/urls")
    public Response.FindList<String> findAll(){
        return FindList.<String>builder()
            .list(urlService.findAll()).build();
    }

    @GetMapping("/ex_redirect3")
    public void exRedirect3(@RequestParam(name = "shortenUrl") String shortenUrl HttpServletResponse httpServletResponse) throws IOException {
        //shortenUrl로 찾기
        httpServletResponse.sendRedirect("https://naver.com");
    }
}
