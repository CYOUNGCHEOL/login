package com.example.login.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name="tb_user")
public class Member {
    @Id
    //@GeneratedValue
    @Column(name="USER_ID")
    private String id;

    @Column(name="USER_NAME")
    private String username;

    @Column(name="USER_PW")
    private String password;

    @Builder
    public Member(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
