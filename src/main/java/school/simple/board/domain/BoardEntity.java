package school.simple.board.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title", length = 20)
    private String title;

    @Column(name = "board_content", length = 500)
    private String content;

    @Override
    public String toString() {
        return "BoardEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Builder
    public BoardEntity BoardBuilder(Long id, String title, String content) {
        return new BoardEntity(id, title, content);
    }
}