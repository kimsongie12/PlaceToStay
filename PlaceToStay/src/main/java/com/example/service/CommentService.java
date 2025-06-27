package com.example.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.entity.*;
import com.example.repository.*;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	
	public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findByPostOrderByCreatedAtAsc(post);
    }

	// 댓글 단건 조회
	public Comment getCommentById(Integer commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다. id=" + commentId));
	}

	// 댓글 수정
	public void updateComment(Integer commentId, String content) {
		Comment comment = getCommentById(commentId);
		comment.setContent(content);
		commentRepository.save(comment);
	}

	// 댓글 삭제
	public void deleteComment(Integer commentId) {
		commentRepository.deleteById(commentId);
	}

	// 댓글 저장
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
	}
}
