package com.example.board;


import com.example.board.controller.BoardController;
import com.example.board.responsedto.BoardEditRequestDto;
import com.example.board.responsedto.BoardRequestDto;
import com.example.board.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
    @InjectMocks
    private BoardController boardController;

    @Mock
    private BoardService boardService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    @DisplayName("게시글 작성 테스트")
    public void writeBoardTest() throws Exception {
        //given
        BoardRequestDto req = new BoardRequestDto("제목","게시글","작성자");

        //when
        mockMvc.perform(
                post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());

        //then
        verify(boardService).save(req);

    }
    @Test
    @DisplayName("전체 게시글 조회 테스트")
    public void getBoardsTest() throws Exception {
        //given x

        //when
        mockMvc.perform(
                get("/api/board")
            ).andExpect(status().isOk());

        //then
        verify(boardService).getBoards();
    }

    @Test
    @DisplayName("단건 게시글 조회 테스트")
    public void getBoardTest() throws Exception {
        //given
        Long id = 1L;

        //when
        mockMvc.perform(
                get("/api/board/{id}",id)
        ).andExpect(status().isOk());

        //then
        verify(boardService).getBoard(id);
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    public void editBoardTest() throws Exception {
        //given
        Long id = 1L;
        BoardEditRequestDto req = new BoardEditRequestDto("제목","내용","작성자");

        //when
        mockMvc.perform(
                put("/api/board/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
        ).andExpect(status().isOk());

        //then
        verify(boardService).editBoard(id,req);
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void deleteBoardTest() throws Exception {
        //given
        Long id = 1L;

        //when
        mockMvc.perform(
                delete("/api/board/{id}",id)
        ).andExpect(status().isOk());

        //then
        verify(boardService).deleteBoard(id);
    }
}
