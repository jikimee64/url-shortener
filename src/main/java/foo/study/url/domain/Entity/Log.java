package foo.study.url.domain.Entity;

import foo.study.url.domain.ReqInfo;
import foo.study.url.domain.ShortenUrl;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ReqInfo reqInfo;

    @Column(name = "original_url")
    private String originalUrl;

    @Embedded
    private ShortenUrl shortenUrl;

    @Column(name = "request_time")
    private String requestTime;

    @Builder
    public Log(ReqInfo reqInfo, String originalUrl, ShortenUrl shortenUrl, String requestTime) {
        this.reqInfo = reqInfo;
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
        this.requestTime = requestTime;
    }

}