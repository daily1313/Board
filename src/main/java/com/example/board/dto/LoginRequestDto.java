package com.example.board.dto;

import com.example.board.entity.Member;
import com.example.board.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "로그인 요청")
public class LoginRequestDto {
    @ApiModelProperty(value = "아이디", notes = "아이디를 입력해주세요", required = true, example = "kimtaesoo")
    @NotBlank(message = "{LoginRequestDto.username.notBlank}")
    private String username;

    @ApiModelProperty(value = "비밀번호", required = true, example = "123456")
    @NotBlank(message = "{LoginRequestDto.password.notBlank}")
    private String password;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
