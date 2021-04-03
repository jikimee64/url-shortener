package foo.study.url.domain;

import foo.study.url.utils.RandomSupplier;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
public class ShortenUrl {

    private static final String BASE_URL = "https://foo.kr/";

    @Column(name = "shorten_url")
    private String shortenUrl;

    @Builder
    public ShortenUrl(String shortenUrl){
        this.shortenUrl = Objects.requireNonNull(shortenUrl);
    }

    public String get(){
        return BASE_URL + this.shortenUrl;
    }
}