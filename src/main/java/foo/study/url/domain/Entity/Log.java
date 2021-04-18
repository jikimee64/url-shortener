package foo.study.url.domain.Entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import lombok.ToString;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
    @AttributeOverride(name = "createdDateTime", column = @Column(name = "CREATED_DATETIME")),
    @AttributeOverride(name = "updatedDateTime", column = @Column(name = "UPDATED_DATETIME"))
})
@Table(name = "tb_log")
public class Log extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_ip")
    private String accessIp;

    @Column(name = "original_url")
    private String originalUrl;

    private String shortenUrl;

    @Column(name = "request_time")
    private String requestTime;

    @Builder
    public Log(String accessIp, String originalUrl, String shortenUrl, String requestTime) {
        this.accessIp = accessIp;
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
        this.requestTime = requestTime;
    }

}