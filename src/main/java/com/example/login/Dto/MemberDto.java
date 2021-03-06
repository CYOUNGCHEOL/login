package com.example.login.Dto;

import com.example.login.Domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private String id;
    private String username;
    private String password;

    //Member 객체로 변환
    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(String id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
