package jscode.board.controller;

import io.swagger.annotations.Api;
import jscode.board.dto.board.BoardRequestDto;
import jscode.board.dto.board.BoardResponseDto;
import jscode.board.dto.comment.CommentRequestDto;
import jscode.board.dto.comment.CommentResponseDto;
import jscode.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Comment Api")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto req, @PathVariable Long id) {
        CommentResponseDto result = commentService.createComment(req, id);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }
}
