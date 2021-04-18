package foo.study.url.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserInfo {

    private String ip;

    public UserInfo(String ip) {
        this.ip = ip;
    }
}
