package foo.study.url.service;

import foo.study.url.domain.Entity.ShortUrl;
import foo.study.url.domain.Entity.Url;
import foo.study.url.domain.ShortenUrl;
import foo.study.url.exception.UrlNotFoundException;
import foo.study.url.repository.ShortUrlRepository;
import foo.study.url.repository.UrlRepository;
import foo.study.url.utils.RandomSupplier;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlService {

    private UrlRepository urlRepository;
    private ShortUrlRepository shortUrlRepository;

    public UrlService(UrlRepository urlRepository,
        ShortUrlRepository shortUrlRepository) {
        this.urlRepository = urlRepository;
        this.shortUrlRepository = shortUrlRepository;
    }

    public String save(Url url) {

        RandomSupplier randomSupplier = new RandomSupplier();

        final ShortenUrl shortenUrl = ShortenUrl.builder()
            .shortenUrl(randomSupplier.get())
            .build();

        ShortUrl entity = new ShortUrl(shortenUrl);

        urlRepository.findByOriginalUrl(url.getOriginalUrl())
            .ifPresentOrElse(
                value -> {
                    entity.setUrl(value);
                    shortUrlRepository.save(entity);
                },
                () -> {
                    url.setShortUrl(entity);
                    urlRepository.save(url);
                }
            );
        return shortenUrl.get();
    }

    public List<String> findAll() {
        return null;
    }

    public String findOriginalUrlByShortedUrl(String shortedUrl) {
        Url url = urlRepository.findOriginalUrlByShortenUrl(shortedUrl)
            .orElseThrow(() -> new UrlNotFoundException("저장된 원본 URL이 없습니다."));
        return url.getOriginalUrl();
    }

}
