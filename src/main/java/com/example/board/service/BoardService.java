package com.example.board.service;


import com.example.board.entity.Board;
import com.example.board.exception.BoardNotFoundException;
import com.example.board.repository.BoardRepository;
import com.example.board.responsedto.BoardEditRequestDto;
import com.example.board.responsedto.BoardRequestDto;
import com.example.board.responsedto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardrepository;


    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards()
    {
        List<Board> boards = boardrepository.findAll();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();

        boards.stream().forEach(i->boardResponseDtoList.add(new BoardResponseDto().toDto(i)));

        return boardResponseDtoList;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id)
    {
        Board board = boardrepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public BoardResponseDto save(BoardRequestDto req)
    {
        Board board = new Board();
        board.setTitle(req.getTitle());
        board.setContent(req.getContent());
        board.setWriter(req.getWriter());
        boardrepository.save(board);

        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public BoardResponseDto editBoard(Long id, BoardEditRequestDto req)
    {
        Board board = boardrepository.findById(id).orElseThrow(BoardNotFoundException::new);

        board.setTitle(req.getTitle());
        board.setContent(req.getContent());

        return BoardResponseDto.toDto(board);
    }

    @Transactional
    public void deleteBoard(Long id)
    {
        Board board = boardrepository.findById(id).orElseThrow(BoardNotFoundException::new);
        boardrepository.delete(board);
    }
}
