package school.simple.board.service;

import lombok.RequiredArgsConstructor;
import school.simple.board.domain.BoardEntity;
import school.simple.board.repository.BoardRepo;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepo boardRepo;

    public Long create(BoardEntity board){
        return boardRepo.save(board).getId();
    }

}
