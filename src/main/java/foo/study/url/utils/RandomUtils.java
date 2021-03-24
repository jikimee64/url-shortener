package foo.study.url.utils;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomUtils {
    private static final Random random = new Random();
    private static final int LEFT_LIMIT = 48; // numeral '0'
    private static final int RIGHT_LIMIT = 122; // numeral 'z'

    private RandomUtils(){}

    public static String generateUrl(final int targetStringLength){
        if(targetStringLength < 1){
            log.info("생성할 문자의 자리수는 한 자리수 이상입니다.");
            throw new IllegalStateException();
        }
        /**
         * filter : 숫자 및 알파벳 소문자 랜덤 생성
         * collect : StringBuffer의 appendCodePoint(int codePoint) 메소드를 사용하여 유니코드에 매칭되는
         * 문자를 append()를 이용해 추가
         */
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
            .filter(i -> (i <= 57 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
            .toString();
    }

}
