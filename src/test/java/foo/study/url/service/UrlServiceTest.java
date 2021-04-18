package foo.study.url.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import foo.study.url.domain.Entity.Url;
import foo.study.url.dto.UrlDto.Response.Select;
import foo.study.url.dto.UserInfo;
import foo.study.url.exception.UrlNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
@SpringBootTest
class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @Test
    @DisplayName("단축 url 저장 후 반환")
    void save() {
        final String url = "http://www.naver.com";
        final int SHORTED_URL_LENGTH = 21;

        Url entity = new Url(url);
        String save = urlService.save(entity);
        assertThat(save.length()).isEqualTo(SHORTED_URL_LENGTH);

    }

    @Test
    @DisplayName("전체 url 조회")
    void find_url() {

        final String url = "http://www.naver.com";
        final String url2 = "http://www.nate.com";
        final int LIST_SIZE = 2;

        UserInfo userInfo = new UserInfo("0.0.0.0");
        Url entity = new Url(url);
        Url entity2 = new Url(url2);
        String shortedUrl = urlService.save(entity);
        String shortedUrl2 = urlService.save(entity2);

        urlService.findOriginalUrlByShortedUrl(shortedUrl, userInfo);
        urlService.findOriginalUrlByShortedUrl(shortedUrl2, userInfo);

        List<Select> all = urlService.findAll();

        assertAll(() -> {
            assertThat(all.size()).isEqualTo(LIST_SIZE);
            assertThat(all.get(0).getId()).isNotNull();
            assertThat(all.get(0).getRequestTime()).isNotNull();
            assertThat(all.get(0).getAccessIp()).isNotNull();
            assertThat(all.get(0).getOriginalUrl()).isNotNull();
            assertThat(all.get(0).getShortenUrl()).isNotNull();
        });

    }

    @Test
    @DisplayName("단축 url를 이용하여 원본url을 구하기")
    void find() {
        final String url = "http://www.naver.com";

        UserInfo userInfo = new UserInfo("0.0.0.0");
        Url entity = new Url(url);
        String shortedUrl = urlService.save(entity);

        String originalUrl = urlService.findOriginalUrlByShortedUrl(shortedUrl, userInfo);

        assertThat(url).isEqualTo(originalUrl);
    }

    @Test
    @DisplayName("단축 url를 이용하여 원본url을 구할시 존재하지 않을때 에러 발생")
    void notFindUrl() {
        UserInfo userInfo = new UserInfo("0.0.0.0");
        final String shortedUrl = "https://foo.kr/wwr0y3";

        assertThrows(UrlNotFoundException.class, () -> {
            urlService.findOriginalUrlByShortedUrl(shortedUrl, userInfo);
        });
    }

}