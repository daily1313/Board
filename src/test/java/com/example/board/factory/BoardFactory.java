package com.example.board.factory;

import com.example.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class BoardFactory {

    public static Board createBoard() {
        return new Board("title", "writer", "content");
    }

    public static Board createBoard(Long id) {

        return new Board(id, "title", "writer", "content");
    }
}
