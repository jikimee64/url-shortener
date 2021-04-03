package foo.study.url.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomSupplierTest {

    @DisplayName("랜덤 URL 생성")
    @Test
    void generate_url(){
        RandomSupplier randomSupplier = new RandomSupplier();
        final int RANGE_URL = 5;
        assertThat(randomSupplier.get()).isEqualTo(RANGE_URL); //생성된 URL 길이 검사
    }

}