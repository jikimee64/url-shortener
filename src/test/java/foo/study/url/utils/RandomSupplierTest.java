package foo.study.url.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomSupplierTest {

    @DisplayName("랜덤 URL 생성")
    @Test
    void generate_url(){
        RandomSupplier randomSupplier = new RandomSupplier();
        final int RANGE_URL = 6;
        assertThat(randomSupplier.get().length()).isEqualTo(RANGE_URL); //생성된 URL 길이 검사
    }

}