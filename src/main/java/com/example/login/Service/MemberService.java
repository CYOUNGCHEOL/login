package com.example.login.Service;

import com.example.login.Domain.Member;
import com.example.login.Dto.MemberDto;
import com.example.login.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    //회원가입
    @Transactional
    //form에서 입력받은 정보를 담은 MemberDto를 받아 password를 암호화를 해준 뒤 MemberDto를 Member객체로 변환하여 JPA를 통해 sava해 줌
    public String signup(MemberDto memberDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        //password를 암호화한 뒤 dp에 저장
        return  memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    //Spring Security가 제공하는 로그인을 사용하기 위해 UserDetailsService를 구현해주어야 함
    //로그인 form에서 입력받은 username을 가지고 DB를 찾은 뒤 있다면 권한 정보를 추가해주어 UserDetails라는 객체로 반환해 줌
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //로그인을 하기위해 가입된 user 정보를 조회하는 메서드
        Optional<Member> memberWrapper = memberRepository.findByusername(username);
        Member member = memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        //여기서는 간단하게 username이 admin이면 admin권한 부여
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        //아이디, 비밀번호,권한리스트를 매개변수로 User를 맏들어 반환
        return new User(member.getUsername(), member.getPassword(), authorities);
    }
}
