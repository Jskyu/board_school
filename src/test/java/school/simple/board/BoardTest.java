package school.simple.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import school.simple.board.domain.BoardEntity;
import school.simple.board.service.BoardService;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
public class BoardTest {

    @Autowired
    BoardService boardService;

    @Test
    void create() {
        //given
        BoardEntity createBoard = BoardEntity.builder()
                .id(1L)
                .title("testTitle")
                .content("testContent")
                .build();

        //when
        Long createId = boardService.create(createBoard);
        BoardEntity findBoard = boardService.findById(createId);

        //then
        Assertions.assertEquals(createId, findBoard.getId(), "생성한 객체와 찾아온 객체의 ID가 같아야 한다.");
    }
}
