package foo.study.url.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class RandomUtilsTest {

    @DisplayName("랜덤 URL 생성")
    @Test
    void generate_url(){
        final int RANGE_URL = 5;
        String shorten_url = RandomUtils.generateUrl(RANGE_URL);
        assertThat(shorten_url.length()).isEqualTo(RANGE_URL); //생성된 URL 길이 검사
    }

}