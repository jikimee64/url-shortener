package foo.study.url.utils;

import static org.assertj.core.api.Assertions.assertThat;

import foo.study.url.repository.ShortUrlRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RandomSupplierTest {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @DisplayName("랜덤 URL 생성")
    @Test
    void generate_url(){

        final String ORIGIN_URL = "http://www.naver.com";

        RandomSupplier randomSupplier = new RandomSupplier(shortUrlRepository);
        final int RANGE_URL = 6;
        assertThat(randomSupplier.apply(ORIGIN_URL).length()).isEqualTo(RANGE_URL); //생성된 URL 길이 검사
    }

}