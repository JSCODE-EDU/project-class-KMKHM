package jscode.board.dto.comment;

import jscode.board.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private String contents;

    public Comment toEntity() {
        return Comment.builder()
                .content(contents)
                .build();
    }
}
