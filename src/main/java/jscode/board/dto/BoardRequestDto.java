package jscode.board.dto;

import jscode.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
