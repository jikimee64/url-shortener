package foo.study.url.aop;

import foo.study.url.dto.UserInfo;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.h2.engine.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    // 파라미터 어노테이션 적용안됨
    //@Around("@annotation(foo.study.url.aop.UserInfoRecord)")
    @Around("execution(* *(.., @UserInfoRecord (*), ..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes())
            .getRequest();

        InetAddress local = InetAddress.getLocalHost();
        String accessIp = local.getHostAddress();

        log.info("여긴 오니 {}", accessIp);

        UserInfo userInfo = new UserInfo(accessIp);

        Object[] args = Arrays.stream(joinPoint.getArgs())
            .map(data -> {
                if (data instanceof UserInfo) {
                    data = userInfo;
                }
                return data;
            })
            .toArray();

        return joinPoint.proceed(args);

    }

}

//
//@Slf4j
//@Getter
//@Embeddable
//@NoArgsConstructor
//public class ReqInfo {
//
//    @Column(name = "access_ip")
//    private String accessIp;
//
//    public void setAccessIp() {
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                .currentRequestAttributes())
//                .getRequest();
//
//            InetAddress local = InetAddress.getLocalHost();
//            this.accessIp = local.getHostAddress();
//        } catch (UnknownHostException e) {
//            log.info(e.getMessage());
//        }
//    }
//}
