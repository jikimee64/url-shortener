package foo.study.url.service;

import foo.study.url.dto.UrlDto.Response;
import foo.study.url.repository.UrlRepository;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    private static final String BASE_URL = "https://foo.kr/";
    private final UrlRepository urlRepository;

    public String save(String url){
        return urlRepository.save(BASE_URL + url);
    }

    public List<String> findAll(){
        return urlRepository.findAll();
    }

}
