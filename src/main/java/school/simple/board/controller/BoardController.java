package school.simple.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.simple.board.dto.BoardDto;
import school.simple.board.service.BoardService;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @RequestMapping
    public String redirect() {
        log.info("/board 접속");
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String board(Model model) {
        log.info("게시판 조회");
        List<BoardDto> list = boardService.findAllToDto();
        model.addAttribute("boards", list);
        return "board";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Model model) {
        log.info(id + "번 게시글 조회");
        model.addAttribute("board", boardService.findById(id).toDto());
        return "view";
    }

    @GetMapping("/write")
    public String write(Model model) {
        log.info("게시글 작성하기");
        model.addAttribute("boardForm", new BoardDto());
        return "write";
    }

    @PostMapping("/write")
    public String writePost(@Valid BoardDto boardForm, Errors errors, Model model) throws NoSuchAlgorithmException {
        log.info("게시글 작성 시도");
        if (errors.hasErrors()) {
            log.warn("게시글 작성 실패. 에러 내용 : " + Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            model.addAttribute("boardForm", boardForm);
            boardService.validateHandling(errors).forEach(model::addAttribute);
            return "write";
        }
        Long id = boardService.create(boardForm);
        log.info("게시글 작성 성공. 글 번호 : " + id);
        return "redirect:view/" + id;
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model) {
        log.info(id + "번 게시글 수정하기");
        model.addAttribute("boardForm", boardService.findById(id).toDto());
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable(value = "id") Long id, @Valid BoardDto boardForm, Errors errors, Model model) throws NoSuchAlgorithmException {
        log.info(id + "번 게시글 수정 시도");
        if (errors.hasErrors()) {
            log.warn("게시글 작성 실패. 에러 내용 : " + Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            model.addAttribute("boardForm", boardForm);
            boardService.validateHandling(errors).forEach(model::addAttribute);
            return "update";
        }
        boardService.update(boardForm);
        log.info(id + "번 게시글 수정 성공");
        return "redirect:/board/view/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        log.info(id + "번 게시글 삭제");
        boardService.deleteById(id);
        return "redirect:/board/list";
    }
}