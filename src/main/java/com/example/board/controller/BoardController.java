package com.example.board.controller;

import com.example.board.response.Response;
import com.example.board.responsedto.BoardEditRequestDto;
import com.example.board.responsedto.BoardRequestDto;
import com.example.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "전체 게시글 조회", notes = "전체 게시글을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board")
    public Response getBoards() {
        return Response.success(boardService.getBoards());
    }

    @ApiOperation(value = "단건 게시글 조회", notes = "단건 게시글을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/board/{id}")
    public Response getBoard(@PathVariable("id") Long id) {
        return Response.success(boardService.getBoard(id));
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성합니다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/board")
    public Response save(@Valid @RequestBody BoardRequestDto boardReq) {
        return Response.success(boardService.save(boardReq));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/board/{id}")
    public Response editBoard(@PathVariable("id") Long id, @Valid @RequestBody BoardEditRequestDto req) {
        return Response.success(boardService.editBoard(id, req));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/board/{id}")
    public Response deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return Response.success("삭제 완료");
    }
}
