package jscode.board.dto;

import jscode.board.domain.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long id;
    private String head;
    private String body;

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getHead(),
                board.getBody()
        );
    }
}
