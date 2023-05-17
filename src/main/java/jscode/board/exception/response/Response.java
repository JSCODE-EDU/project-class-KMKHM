package jscode.board.exception.response;

import jscode.board.exception.type.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class Response {

    private int code;

    private String message;


    public Response(ExceptionType exceptionType) {
        this.code = exceptionType.getStatus();
        this.message = exceptionType.getMessage();
    }

}
