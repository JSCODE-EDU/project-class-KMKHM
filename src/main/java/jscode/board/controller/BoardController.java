package jscode.board.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jscode.board.domain.Board;
import jscode.board.dto.board.BoardRequestDto;
import jscode.board.dto.board.BoardResponseDto;
import jscode.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(tags = "Board Api")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글 생성 기능", notes = "제목과 본문을 입력받아 게시글 생성")
    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> createBoard(@Valid @RequestBody BoardRequestDto req) {
        BoardResponseDto board = boardService.createBoard(req);
        return new ResponseEntity(board, HttpStatus.CREATED);
    }

    @ApiOperation(value = "모든 게시글 조회", notes = "게시글 생성일 기준으로 내림차순으로 최대 100개 조회")
    @GetMapping("/boards")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Board>> findAllBoards() {
        List<Board> allBoards = boardService.findAllBoards();
        return ResponseEntity.ok().body(allBoards);
    }

    @ApiOperation(value = "특정 게시글 조회", notes = "id를 입력받아 특정 게시글 하나를 조회")
    @GetMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BoardResponseDto> findBoard(@PathVariable Long id) {
        BoardResponseDto board = boardService.findBoard(id);
        return ResponseEntity.ok().body(board);
    }

    @ApiOperation(value = "게시글 수정", notes = "특정 게시글 하나를 수정")
    @PutMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BoardResponseDto> editBoard(@PathVariable Long id, @Valid @RequestBody BoardRequestDto req) {
        BoardResponseDto result = boardService.editBoard(id, req);
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "게시글 삭제", notes = "특정 게시글 하나를 삭제")
    @DeleteMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }

    @ApiOperation(value = "게시글 검색", notes = "제목을 기준으로 게시글 검색")
    @GetMapping("/boards/searching/{title}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Board>> searchBoardByTitle(@NotBlank @PathVariable String title) {
        List<Board> results = boardService.findBoardByHead(title);
        return ResponseEntity.ok().body(results);
    }

    @ApiOperation(value = "좋아요", notes = "특정 게시글 좋아요 표시 or 취소")
    @PostMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> likeBoard(@PathVariable Long id) {
        String result = boardService.likeBoard(id);
        return ResponseEntity.ok().body(result);
    }

}
