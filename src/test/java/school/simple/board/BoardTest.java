package school.simple.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import school.simple.board.domain.BoardEntity;
import school.simple.board.dto.BoardDto;
import school.simple.board.service.BoardService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@SpringBootTest
@Transactional
public class BoardTest {

    @Autowired
    BoardService boardService;

    @Test
    void create() {
        //given
        BoardDto form = new BoardDto(15648974L, "testTitle", "testContent", LocalDateTime.now());

        //when
        Long createId = boardService.create(form);
        BoardEntity findBoard = boardService.findById(createId);

        //then
        Assertions.assertEquals(createId, findBoard.getId(), "생성한 객체와 찾아온 객체의 ID가 같아야 한다.");
    }
}
