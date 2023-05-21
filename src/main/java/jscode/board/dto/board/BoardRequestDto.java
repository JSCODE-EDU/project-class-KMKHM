package jscode.board.dto.board;

import jscode.board.domain.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    @Length(min = 1, max = 15)
    private String head;

    @NotBlank(message = "내용은 필수입니다.")
    @Length(min = 1, max = 1000)
    private String body;

    public Board toEntity() {
        return Board.builder()
                .head(head)
                .body(body)
                .build();
    }
}
