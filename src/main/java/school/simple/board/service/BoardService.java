package school.simple.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import school.simple.board.domain.BoardEntity;
import school.simple.board.dto.BoardDto;
import school.simple.board.repository.BoardRepo;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepo boardRepo;

    @Transactional
    public Long create(BoardDto form) {
        return boardRepo.save(BoardEntity.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .createTime(LocalDateTime.now())
                .build()).getId();
    }

    @Transactional
    public void update(BoardDto form){
        BoardEntity find = findById(form.getId());
        if(form.getPassword().equals(find.getPassword())){
            find.update(form);
        }
    }

    public BoardEntity findById(Long id) {
        return boardRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Not Found Entity. ID : " + id));
    }

    public List<BoardEntity> findAll() {
        return boardRepo.findAll();
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        errors.getFieldErrors().forEach(e ->
                validatorResult.put(String.format("valid_%s", e.getField()), e.getDefaultMessage()));

        return validatorResult;
    }

    public void deleteById(Long id) {
        boardRepo.deleteById(id);
    }

    public List<BoardDto> findAllToDto() {
        return this.findAll().stream().map(BoardEntity::toDto).collect(Collectors.toList());
    }
}
