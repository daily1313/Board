package com.example.board.controller;

import com.example.board.dto.MemberDto;
import com.example.board.response.Response;
import com.example.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Response findAll() {
        return Response.success(memberService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}")
    public Response findUser(@PathVariable("userId") Long userId) {
        return Response.success(memberService.findUser(userId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public Response register(@RequestBody MemberDto memberDto) {
        return Response.success(memberService.register(memberDto));
    }
}
