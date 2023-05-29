package jscode.board.dto.comment;

import jscode.board.domain.Comment;
import jscode.board.domain.Member;
import jscode.board.dto.member.MemberResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {
    private String contents;
    private String createDate;


    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto (
                comment.getContent(),
                comment.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
        );
    }
}
