package jscode.board.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    BOARD_NOT_FOUND_EXCEPTION(404, "게시글이 존재하지 않습니다."),
    EMAIL_ALREADY_EXISTS_EXCEPTION(400, "이미 존재하는 이메일입니다.")
    ;

    private final int status;
    private final String message;
}
