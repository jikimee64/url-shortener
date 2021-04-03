package foo.study.url.domain;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Getter
@Embeddable
@NoArgsConstructor
public class ReqInfo {

    @Column(name = "access_ip")
    private String accessIp;

    public void setAccessIp() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

            InetAddress local = InetAddress.getLocalHost();
            this.accessIp = local.getHostAddress();
        } catch (UnknownHostException e) {
            log.info(e.getMessage());
        }
    }
}
