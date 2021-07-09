package school.simple.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.simple.board.domain.BoardEntity;
import school.simple.board.repository.BoardRepo;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final BoardRepo boardRepo;

    public Long create(BoardEntity board){
        return boardRepo.save(board).getId();
    }

    public BoardEntity findById(Long id) {
        return boardRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Not Found Entity. ID : " + id));
    }
}
