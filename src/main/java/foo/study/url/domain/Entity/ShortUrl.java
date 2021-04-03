package foo.study.url.domain.Entity;

import foo.study.url.domain.ShortenUrl;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "short_url")
@ToString(exclude = "url")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Embedded
    private ShortenUrl shortenUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private Url url;

    public ShortUrl(ShortenUrl shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    public void setUrl(Url url){
        this.url = url;
    }

}
