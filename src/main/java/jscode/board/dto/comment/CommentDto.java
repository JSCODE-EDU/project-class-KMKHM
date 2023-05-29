package jscode.board.dto.comment;

import jscode.board.domain.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String Contents;
    private String createdDate;
    private String email;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(
        comment.getContent(),
        comment.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
        comment.getMember().getEmail()
        );
    }
}
