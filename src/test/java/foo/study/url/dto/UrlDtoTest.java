package foo.study.url.dto;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import foo.study.url.dto.UrlDto.Request;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;

class UrlDtoTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init(){
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @DisplayName("url에 빈값 전송시 에러발생")
    @Test
    void save_notblank_validation(){
        Request.Save saveDto = Request.Save.builder().url("").build();

        // 유효하다면 violations는 빈 값, 유효하지 않다면 값을 가지고 있음.
        Set<ConstraintViolation<Request.Save>> violations = validator.validate(saveDto, ValidationGroups.NotEmptyGroup.class);

        violations
            .forEach(error -> {
                assertThat(error.getMessage()).isEqualTo("Link 필드는 필수입니다.");
            });
    }

    @DisplayName("url에 http 표현식이 아닌 값 전송시 에러발생")
    @Test
    void save_pattern_validation(){
        Request.Save saveDto = Request.Save.builder().url("not http expressions").build();

        Set<ConstraintViolation<Request.Save>> violations = validator.validate(saveDto, ValidationGroups.PatternCheckGroup.class);

        violations
            .forEach(error -> {
                assertThat(error.getMessage()).isEqualTo("Link 형식이 유효하지 않습니다.");
            });
    }

}