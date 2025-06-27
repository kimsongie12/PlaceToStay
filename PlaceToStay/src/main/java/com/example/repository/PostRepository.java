package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByOrderByCreatedAtDesc();
    
 // 검색어 포함된 제목만 가져오기 (동시에 최신순 정렬 + 페이징)
    Page<Post> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String keyword, Pageable pageable);
    
    Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}