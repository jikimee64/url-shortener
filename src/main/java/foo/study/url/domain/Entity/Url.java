package foo.study.url.domain.Entity;

import foo.study.url.domain.ShortenUrl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@AttributeOverrides({
    @AttributeOverride(name = "createdDateTime", column = @Column(name = "CREATED_DATETIME")),
    @AttributeOverride(name = "updatedDateTime", column = @Column(name = "UPDATED_DATETIME"))
})
@Table(name = "tb_url")
@ToString(exclude = "shortUrl")
public class Url extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url",unique = true)
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
