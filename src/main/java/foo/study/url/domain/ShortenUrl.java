package foo.study.url.domain;

import foo.study.url.domain.Entity.Log;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Embeddable
@NoArgsConstructor
public class ShortenUrl {

    private static final String BASE_URL = "https://foo.kr/";

    @Column(name = "shorten_url")
    private String shortenUrl;

    public ShortenUrl(String shortenUrl){
        this.shortenUrl = Objects.requireNonNull(shortenUrl);
    }

    public String get(){
        return this.shortenUrl;
    }

}