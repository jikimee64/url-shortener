package foo.study.url.domain.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "url")
@ToString(exclude = "shortUrl")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @OneToMany(mappedBy = "url", cascade = CascadeType.ALL)
    private List<ShortUrl> shortUrl = new ArrayList<>();

    public Url(String originalUrl) {
        this.originalUrl = Objects.requireNonNull(originalUrl);
    }

    public void setShortUrl(ShortUrl shortUrl){
        Objects.requireNonNull(shortUrl);
        shortUrl.setUrl(this);
        getShortUrl().add(shortUrl);
    }
}
