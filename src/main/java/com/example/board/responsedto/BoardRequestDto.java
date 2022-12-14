package com.example.board.responsedto;

import com.example.board.entity.Board;
import com.example.board.entity.User;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    @NotBlank(message = "작성자를 입력해 주세요")
    private String writer;
    @NotBlank(message = "내용을 입력해 주세요")
    private String content;

    private User user;

    public static Board toDto(BoardRequestDto req){
        return new Board(req.getTitle(),req.getWriter(),req.getContent(),req.getUser());
    }

}
