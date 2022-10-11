package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.responsedto.BoardEditRequestDto;
import com.example.board.responsedto.BoardRequestDto;
import com.example.board.responsedto.BoardResponseDto;
import com.example.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.board.factory.BoardFactory.createBoard;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 전체 조회 테스트")
    public void getBoardsTest() {
        //given
        List<Board> boards = new ArrayList<>();

        Board b1 = new Board("제목", "작성자", "게시글");
        Board b2 = new Board("제목", "작성자", "게시글");

        boards.add(b1);
        boards.add(b2);

        given(boardRepository.findAll()).willReturn(boards);

        //whe
        List<BoardResponseDto> result = boardService.getBoards();
        //then
        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("게시글 단건 조회 테스트")
    public void getBoardTest() {
        //given
        Board board = new Board("제목", "작성자", "게시글");
        given(boardRepository.findById(anyLong())).willReturn(Optional.of(board));

        //when
        BoardResponseDto res = boardService.getBoard(1L);


        //then
        assertThat(res.getContentDto()).isEqualTo(board.getContent());
    }

    @Test
    @DisplayName("게시글 작성 테스트")
    public void writeBoardTest() {
        //given
        Board board = new Board("제목", "작성자", "게시글");
        BoardRequestDto boardRequestDto = new BoardRequestDto("제목", "작성자", "게시글");
        given(boardRepository.save(board)).willReturn(board);
        //when
        boardService.save(boardRequestDto);
        //then
        verify(boardRepository).save(any());
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    public void editBoardTest() {
        Board board = new Board(1L, "제목", "내용", "작성자");
        BoardEditRequestDto req = new BoardEditRequestDto("제목", "내용2", "작성자");
        given(boardRepository.findById(anyLong())).willReturn(Optional.of(board));

        boardService.editBoard(1L, req);

        assertThat(board.getContent()).isEqualTo("내용2");

    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void deleteBoardTest() {
        // given
        Long id = 1L;
        Board board = createBoard(id);
        given(boardRepository.findById(id)).willReturn(Optional.of(board));

        // when
        boardService.deleteBoard(id);

        // then
        verify(boardRepository).delete(board);
    }

}
