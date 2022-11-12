package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.entity.User;
import com.example.board.exception.UserNotFoundException;
import com.example.board.repository.UserRepository;
import com.example.board.response.Response;
import com.example.board.responsedto.BoardEditRequestDto;
import com.example.board.responsedto.BoardRequestDto;
import com.example.board.responsedto.BoardResponseDto;
import com.example.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;
    @ApiOperation(value = "전체 게시글 조회", notes = "전체 게시글을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/boards")
    public Response getBoards() {
        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        return Response.success(boardService.getBoards(user));
    }
    @ApiOperation(value = "단건 게시글 조회", notes = "단건 게시글을 조회합니다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/boards/{boardId}")
    public Response getBoard(@PathVariable("boardId") Long boardId,@PathVariable("userId") Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return Response.success(boardService.getBoard(boardId,user));
    }
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성합니다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/users/{userId}/boards")
    public  Response writeBoard(@RequestBody BoardRequestDto boardRequestDto) {
        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        return Response.success(boardService.save(boardRequestDto,user));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{userId}/boards/{boardId}")
    public Response modifyBoard(@PathVariable("boardId") Long boardId, @PathVariable("userId") Integer userId, @RequestBody BoardEditRequestDto boardEditRequestDto) {

        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        return Response.success(boardService.editBoard(boardId,boardEditRequestDto, user));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId) {
        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        boardService.deleteBoard(boardId,user);

    }
}
