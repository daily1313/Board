package com.example.board.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
    //인스턴스 생성하면 자동으로 인덱스를 넣어라 !

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    public Board(String title, String writer, String content)
    {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

}
