package foo.study.url.domain.Entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
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

    @Column(name = "access_ip")
    private String accessIp;

    @Column(name = "request_time")
    private LocalDateTime requestTime;

    @Builder
    public Log(String accessIp, LocalDateTime requestTime){
        this.accessIp = accessIp;
        this.requestTime = requestTime;
    }

}
