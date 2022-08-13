package com.example.board.controller;

import com.example.board.response.Response;
import com.example.board.responsedto.BoardEditRequestDto;
import com.example.board.responsedto.BoardRequestDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board")
    public Response getBoards(){
        return Response.success(boardService.getBoards());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/{id}")
    public Response getBoard(@PathVariable("id") Long id)
    {
        return Response.success(boardService.getBoard(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board")
    public Response save(@Valid @RequestBody BoardRequestDto boardReq)
    {
        return Response.success(boardService.save(boardReq));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/board/{id}")
    public Response editBoard(@PathVariable("id") Long id, @Valid @RequestBody BoardEditRequestDto req)
    {
        return Response.success(boardService.editBoard(id,req));
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/board/{id}")
    public Response deleteBoard(@PathVariable("id") Long id)
    {
        boardService.deleteBoard(id);
        return Response.success("삭제 완료");
    }
}
