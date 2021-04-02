package foo.study.url.service;

import foo.study.url.domain.ShortenUrl;
import foo.study.url.domain.Url;
import foo.study.url.dto.UrlDto.Response;
import foo.study.url.repository.UrlRepository;
import foo.study.url.utils.RandomSupplier;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {


    private final UrlRepository urlRepository;

    public String save(Url url){
        RandomSupplier randomSupplier = new RandomSupplier();

        ShortenUrl shortenUrl = ShortenUrl.builder()
            .shortenUrl(randomSupplier.get())
            .build();

        Url save = Url.builder()
            .originalUrl(url.getOriginalUrl())
            .shortenUrl(shortenUrl)
            .build();

        urlRepository.save(save);

        return shortenUrl.get();
    }

    public List<String> findAll(){

        return urlRepository.findAll();
    }

}
