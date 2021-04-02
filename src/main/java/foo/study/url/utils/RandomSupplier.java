package foo.study.url.utils;

import java.util.Random;
import java.util.function.Supplier;

public class RandomSupplier implements Supplier<String>{
    private static final int URL_RANGE = 6;
    private static final int LEFT_LIMIT = 48; // numeral '0'
    private static final int RIGHT_LIMIT = 122; // numeral 'z'

    @Override
    public String get() {
        return new Random().ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
            .filter(i -> (i <= 57 || i >= 97))
            .limit(URL_RANGE)
            .collect(StringBuffer::new, StringBuffer::appendCodePoint, StringBuffer::append)
            .toString();
    }
}
