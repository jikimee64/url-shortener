package foo.study.url.dto;

import foo.study.url.domain.Entity.Url;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UrlDto {

    public static class Request{
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Save{
            @NotBlank(message = "Link 필드는 필수입니다.", groups = ValidationGroups.NotEmptyGroup.class)
            @Pattern(regexp="^((http(s?))\\:\\/\\/)([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$",
                message = "Link 형식이 유효하지 않습니다.", groups = ValidationGroups.PatternCheckGroup.class)
            private String url;
        }

        public static Url toEntity(Save dto){
            return new Url(dto.getUrl());
        }
    }

    public static class Response{
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class FindOne<T>{
            private T data;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class FindList<T>{
            private List<T> list;
        }
    }
}
