package com.example.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Member;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
    private final MemberRepository memberRepository;

    public Member getById(Integer id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));
    }

    /**  이메일로 회원 조회  */
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)          // ← 이미 선언돼 있음
                               .orElseThrow(() ->
                                   new UsernameNotFoundException("해당 이메일 사용자를 찾을 수 없습니다: " + email));
    }
    
//    public Member findByUsername(String username) {
//        return memberRepository.findByUsername(username)
//            .orElseThrow(() -> new UsernameNotFoundException("해당 유저 없음"));
//    }
    
//    public Member findByNickname(String nickname) {
//        return memberRepository.findByNickname(nickname)
//                .orElse(null);
//    }
    
    
}
