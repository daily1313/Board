package com.example.board.dto;

import com.example.board.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String username;
    private String password;
    private String nickname;

    public static MemberDto toDto(Member member) {
        return new MemberDto(
                member.getUsername(),
                member.getPassword(),
                member.getNickname()
        );
    }
}
