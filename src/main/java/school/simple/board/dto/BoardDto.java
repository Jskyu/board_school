package school.simple.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;

    @NotBlank(message = "제목은 필수 입력입니다.")
    @Length(max = 20, message = "제목은 최대 20글자 입니다.")
    private String title;
    private String content;
    private String createTime;

    public BoardDto(Long id, String title, String content, LocalDateTime createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분"));
    }
}