package school.simple.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import school.simple.board.dto.BoardDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title", length = 20)
    private String title;

    @Column(name = "board_content", length = 500)
    private String content;

    @Column(name = "board_create_time")
    private LocalDateTime createTime;

    @Column(name = "board_password", length = 200)
    private byte[] password;

    @Override
    public String toString() {
        return "BoardEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Builder
    public BoardEntity(Long id, String title, String content, LocalDateTime createTime, byte[] password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.password = password;
    }

    public BoardDto toDto() {
        return new BoardDto(id, title, content, createTime);
    }

    public void update(BoardDto form) {
        this.title = form.getTitle();
        this.content = form.getContent();
    }
}