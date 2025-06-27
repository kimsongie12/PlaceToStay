package com.example.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommentRequestDto;
import com.example.dto.CommentUpdateDto;
import com.example.entity.Comment;
import com.example.entity.Member;
import com.example.entity.ReactionType;
import com.example.entity.TargetType;
import com.example.service.CommentService;
import com.example.service.MemberService;
import com.example.service.PostService;
import com.example.service.ReactionService;

import lombok.RequiredArgsConstructor;

//CommentController.java 예시

@Controller
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final PostService postService;
	private final MemberService memberService;
	private final ReactionService reactionService;

	// 댓글 수정 폼 페이지 (GET)
	@GetMapping("/{commentId}/edit")
	public String editForm(@PathVariable Integer postId, @PathVariable Integer commentId, Model model) {
		Comment comment = commentService.getCommentById(commentId);
		model.addAttribute("comment", comment);
		model.addAttribute("postId", postId);
		return "comment/comment_edit"; // 아래에서 만들 html 파일명
	}

	// 댓글 수정 처리 (POST)
	@PostMapping("/{commentId}/edit")
	public String edit(@PathVariable Integer postId, @PathVariable Integer commentId, @RequestParam String content) {
		commentService.updateComment(commentId, content);
		return "redirect:/posts/" + postId;
	}

	// 댓글 삭제 처리 (POST)
	@PostMapping("/{commentId}/delete")
	public String delete(@PathVariable Integer postId, @PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		return "redirect:/posts/" + postId;
	}

	// 댓글 좋아요 싫어요 처리
	@PostMapping("/{commentId}/react")
	public String reactToComment(@PathVariable Integer postId,
	                             @PathVariable Integer commentId,
	                             @RequestParam ReactionType reactionType,
	                             Principal principal) {

//	    Member member = memberService.findByUsername(principal.getName()); // 로그인 사용자
//	    reactionService.react(member, TargetType.COMMENT, commentId, reactionType);

	    return "redirect:/posts/" + postId;
	}
}
