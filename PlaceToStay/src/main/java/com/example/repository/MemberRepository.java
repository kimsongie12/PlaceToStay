package com.example.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Member;
import com.example.entity.Post;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // 추가 쿼리 메서드는 여기서 정의 가능
	//Optional<Member> findByUsername(String username);
	
	@Query("select p from Post p join fetch p.member where p.title like %:keyword%")
	Page<Post> findByTitleWithMember(@Param("keyword") String keyword, Pageable pageable);

	
	//Optional<Member> findByNickname(String nickname);
	
	//구글 사용자 검색 메서드 추가
	Optional<Member> findByEmail(String email);
}