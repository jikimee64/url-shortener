package foo.study.url.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import foo.study.url.repository.UrlRepository;
import foo.study.url.repository.UrlRepositoryImpl;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class UrlServiceTest {

    private static UrlRepository urlRepository;
    private static UrlService urlService;
    private static ConcurrentHashMap<String, String> urlLists;

    @BeforeAll
    public static void init(){
        urlLists = new ConcurrentHashMap<>();
        urlRepository = new UrlRepositoryImpl(urlLists);
        urlService = new UrlService(urlRepository);
    }

    @DisplayName("url 저장 후 shortedUrl 반환")
    @Test
    public void save(){
        String url = "https://www.google.com";
        String shortedUrl = urlService.save(url);

        assertNotNull(shortedUrl);
        assertThat(shortedUrl.length()).isEqualTo(5);
    }

    @DisplayName("같은 url 전송시 같은 shortenUrl 반환")
    @Test
    public void same_url(){
        String url = "https://www.google.com";
        String shortedUrl = urlService.save(url);

        String url2 = "https://www.google.com";
        String shortedUrl2 = urlService.save(url2);

        assertAll(() -> {
            assertNotNull(shortedUrl);
            assertNotNull(shortedUrl2);
            assertThat(shortedUrl.length()).isEqualTo(shortedUrl2.length());
        });
    }

    @DisplayName("저장된 모든 URL 반환")
    @Order(2)
    @Test
    public void findAll(){

        String url = "https://www.google.com";
        String shortedUrl = urlService.save(url);

        String url2 = "https://www.naver.com";
        String shortedUrl2 = urlService.save(url2);

        String url3 = "https://www.github.com";
        String shortedUrl3 = urlService.save(url3);

        List<String> all = urlService.findAll();

        assertAll(() -> {
            assertNotNull(shortedUrl);
            assertNotNull(shortedUrl2);
            assertNotNull(shortedUrl3);
            assertThat(all.size()).isEqualTo(3);
        });

    }

}