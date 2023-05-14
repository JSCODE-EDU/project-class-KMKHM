package jscode.board.controller;

import jscode.board.domain.Board;
import jscode.board.dto.BoardRequestDto;
import jscode.board.dto.BoardResponseDto;
import jscode.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto req) {
        BoardResponseDto board = boardService.createBoard(req);
        return new ResponseEntity(board, HttpStatus.CREATED);
    }

    @GetMapping("/boards")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Board>> findAllBoards() {
        List<Board> allBoards = boardService.findAllBoards();
        return ResponseEntity.ok().body(allBoards);
    }

    @GetMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BoardResponseDto> findBoard(@PathVariable Long id) throws Exception {
        BoardResponseDto board = boardService.findBoard(id);
        return ResponseEntity.ok().body(board);
    }

    @PutMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BoardResponseDto> editBoard(@PathVariable Long id, @RequestBody BoardRequestDto req) throws Exception {
        BoardResponseDto result = boardService.editBoard(id, req);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long id) throws Exception {
        boardService.deleteBoard(id);
    }

    @GetMapping("/boards/searching/{title}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Board>> searchBoardByTitle(@PathVariable String title) {
        List<Board> results = boardService.findBoardByHead(title);
        return ResponseEntity.ok().body(results);
    }
}
