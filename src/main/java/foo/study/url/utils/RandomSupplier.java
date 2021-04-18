package foo.study.url.utils;

import foo.study.url.repository.ShortUrlRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.bitcoinj.core.Base58;

public class RandomSupplier implements Function<String, String> {
    private static final int URL_RANGE = 6;
    private final ShortUrlRepository shortUrlRepository;

    public RandomSupplier(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    /**
     * URL Hash Salt 적용하여 해시 -> 자르고 base64 적용해보기
     */
    @Override
    public String apply(String originUrl) {
        String url = generateUrl(originUrl);
        while (!isUnique(url)){
            url = generateUrl(originUrl);
        }
        return url;
    }

    private String generateUrl(String originUrl){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-256");
            String salt = SHA256Util.generateSalt();
            md.update(salt.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Hash SHA-256 Error...");
        }
        return encodeBase64(md.digest(originUrl.getBytes(StandardCharsets.UTF_8)));
    }

    private String encodeBase64(byte[] source){
        String encode = Base58.encode(source);
        return encode.substring(0, URL_RANGE);
    }

    public boolean isUnique(String url){
        return !shortUrlRepository.existsByShortenUrl(url);
    }
}
