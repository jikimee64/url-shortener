package foo.study.url.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DateType {

    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private String dateTime = LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"));

}
