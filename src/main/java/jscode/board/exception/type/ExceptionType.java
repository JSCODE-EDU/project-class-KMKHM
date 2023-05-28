package jscode.board.exception.type;

import jscode.board.exception.jwt.InvalidRefreshTokenException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    BOARD_NOT_FOUND_EXCEPTION(404, "게시글이 존재하지 않습니다."),
    EMAIL_ALREADY_EXISTS_EXCEPTION(400, "이미 존재하는 이메일입니다."),
    NOT_FOUND_MEMBER_EXCEPTION(404, "존재하지 않는 멤버입니다."),
    INVALID_REFRESH_TOKEN_EXCEPTION(401, "Refresh Token이 유효하지 않습니다."),
    LOGOUT_EXCEPTION(401, "로그아웃된 사용자입니다."),
    INVALID_TOKEN_EXCEPTION(401, "토큰이 잘못되었습니다.")
    ;

    private final int status;
    private final String message;
}
