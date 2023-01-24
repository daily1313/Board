package com.example.board.service;

import com.example.board.dto.MemberDto;
import com.example.board.entity.Member;
import com.example.board.exception.UserNotFoundException;
import com.example.board.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member register(MemberDto memberDto) {

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .build();

        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findUser(Long id) {
        return memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
