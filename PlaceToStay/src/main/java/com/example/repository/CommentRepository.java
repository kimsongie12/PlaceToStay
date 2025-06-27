package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Comment;
import com.example.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPostOrderByCreatedAtAsc(Post post);
}
