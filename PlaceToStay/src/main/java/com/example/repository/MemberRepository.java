package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // 추가 쿼리 메서드는 여기서 정의 가능
	//Optional<Member> findByUsername(String username);
}