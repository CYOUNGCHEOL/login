package com.example.login.Repository;

import com.example.login.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository<>를 상속받아 Repository를 만들어 주고 UserDetailService에서 username으로 회원을 검색할 수 있도록 메서드를 정의
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByusername(String username);
}
