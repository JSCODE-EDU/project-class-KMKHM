package jscode.board.dto.member;

import jscode.board.domain.Board;
import jscode.board.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long id;
    private String head;
    private String createDate;


    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                member.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
        );
    }
}
