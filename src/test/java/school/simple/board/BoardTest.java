package school.simple.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import school.simple.board.domain.BoardEntity;
import school.simple.board.dto.BoardDto;
import school.simple.board.service.BoardService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@Transactional
public class BoardTest {

    @Autowired
    BoardService boardService;

    @Test
    void create() {
        //given
        BoardDto form = new BoardDto(15648974L, "testTitle", "testContent", LocalDateTime.now(), "1234");

        //when
        Long createId = boardService.create(form);
        BoardEntity findBoard = boardService.findById(createId);

        //then
        assertEquals(createId, findBoard.getId(), "생성한 객체와 찾아온 객체의 ID가 같아야 한다.");
    }

    @Test
    void delete() {
        //given
        Long createId = boardService.create(new BoardDto(4561561L, "deleteTest", "", LocalDateTime.now(), "1234"));

        //when
        boardService.deleteById(createId);

        try {
            boardService.findById(createId);
        } catch (NoSuchElementException e) {
            System.out.println("error : " + e.getMessage());
            return;
        }

        //then
        fail("오류가 발생해야 함");
    }
}
