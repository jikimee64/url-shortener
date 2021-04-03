package foo.study.url.service;

import foo.study.url.domain.DateType;
import foo.study.url.domain.Entity.Log;
import foo.study.url.domain.Entity.ShortUrl;
import foo.study.url.domain.Entity.Url;
import foo.study.url.domain.ReqInfo;
import foo.study.url.domain.ShortenUrl;
import foo.study.url.dto.UrlDto.Response.Select;
import foo.study.url.exception.UrlNotFoundException;
import foo.study.url.repository.LogRepository;
import foo.study.url.repository.ShortUrlRepository;
import foo.study.url.repository.UrlRepository;
import foo.study.url.utils.RandomSupplier;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlService {

    private UrlRepository urlRepository;
    private ShortUrlRepository shortUrlRepository;
    private LogRepository logRepository;

    public UrlService(UrlRepository urlRepository,
        ShortUrlRepository shortUrlRepository, LogRepository logRepository) {
        this.urlRepository = urlRepository;
        this.shortUrlRepository = shortUrlRepository;
        this.logRepository = logRepository;
    }

    public String save(Url url) {

        RandomSupplier randomSupplier = new RandomSupplier();

        final ShortenUrl shortenUrl = new ShortenUrl(randomSupplier.get());

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

    public List<Select> findAll() {
        List<Log> entity = logRepository.findAll();

        List<Select> collect = entity.stream()
            .map(Select::toDto)
            .collect(Collectors.toList());

        return collect;
    }

    public String findOriginalUrlByShortedUrl(String shortedUrl) {
        ShortenUrl shortenUrl = new ShortenUrl(shortedUrl);
        shortenUrl.filter(shortedUrl);

        Url url = urlRepository.findOriginalUrlByShortenUrl(shortenUrl)
            .orElseThrow(() -> new UrlNotFoundException("저장된 원본 URL이 없습니다."));

        //로깅
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setAccessIp();

        Log log = Log.builder()
            .reqInfo(reqInfo)
            .originalUrl(url.getOriginalUrl())
            .shortenUrl(shortenUrl)
            .requestTime(new DateType().getDateTime())
            .build();

        logRepository.save(log);

        return url.getOriginalUrl();
    }

}
