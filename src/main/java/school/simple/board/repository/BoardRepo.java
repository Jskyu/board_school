package school.simple.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.simple.board.domain.BoardEntity;

public interface BoardRepo extends JpaRepository<BoardEntity, Long> {
}
