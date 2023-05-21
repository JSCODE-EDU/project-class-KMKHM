package jscode.board.exception.advice;

import jscode.board.exception.board.BoardNotFoundException;
import jscode.board.exception.member.EmailAlreadyExistsException;
import jscode.board.exception.response.Response;
import jscode.board.exception.type.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException() {
        return new Response(ExceptionType.BOARD_NOT_FOUND_EXCEPTION);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response emailAlreadyExistsException() {
        return new Response(ExceptionType.EMAIL_ALREADY_EXISTS_EXCEPTION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new Response(400 ,e.getBindingResult().getFieldError().getDefaultMessage());
    }


}
