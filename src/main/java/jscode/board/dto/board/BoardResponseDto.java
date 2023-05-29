package jscode.board.dto.board;

import jscode.board.domain.Board;
import jscode.board.domain.Comment;
import jscode.board.dto.comment.CommentDto;

import jscode.board.dto.comment.CommentResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long id;
    private String head;
    private String body;

    private List<CommentDto> comments;


    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getHead(),
                board.getBody(),
                board.getComments().stream().map(CommentDto::toDto).collect(toList())
        );
    }
}
