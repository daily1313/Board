package com.example.board.advice;

import com.example.board.exception.BoardNotFoundException;
import com.example.board.exception.WriterNotFoundException;
import com.example.board.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException()
    {
        return Response.failure(404,"게시글을 찾을 수 없습니다.");
    }

    @ExceptionHandler(WriterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response writerNotFoundException()
    {
        return Response.failure(404,"작성자를 찾을 수 없습니다.");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) { // 2
        return Response.failure(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
