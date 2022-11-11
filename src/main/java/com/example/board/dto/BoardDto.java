package com.example.board.dto;



import com.example.board.entity.Board;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    String writer;
    String title;
    String content;
    Integer userId;

    public static BoardDto toDto(Board board) {
        return new BoardDto(
                board.getWriter(),
                board.getTitle(),
                board.getContent(),
                board.getUser().getId()
        );
    }
}
