package com.example.board.responsedto;

import com.example.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    private String titleDto;
    private String contentDto;
    private String writerDto;
    public static BoardResponseDto toDto(Board board)
    {
        return new BoardResponseDto(
                board.getTitle(),
                board.getContent(),
                board.getWriter()
        );
    }
}
