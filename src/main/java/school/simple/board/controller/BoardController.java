package school.simple.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.simple.board.domain.BoardEntity;
import school.simple.board.dto.BoardDto;
import school.simple.board.service.BoardService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String board(Model model) {
        List<BoardDto> list = boardService.findAllToDto();
        model.addAttribute("boards", list);
        return "board";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("board", boardService.findById(id).toDto());
        return "view";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("boardForm", new BoardDto());
        return "write";
    }

    @PostMapping("/write")
    public String writePost(@Valid BoardDto boardForm, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("boardForm", boardForm);
            boardService.validateHandling(errors).forEach(model::addAttribute);
            return "write";
        }
        return "redirect:view/" + boardService.create(boardForm);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        boardService.deleteById(id);
        return "redirect:/board/list";
    }
}