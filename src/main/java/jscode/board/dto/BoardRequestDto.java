package jscode.board.dto;

import jscode.board.domain.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    private String head;
    private String body;

    public Board toEntity() {
        return Board.builder()
                .head(head)
                .body(body)
                .build();
    }
}
