package com.example.controller;

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
import com.example.service.CommentService;
import com.example.service.PostService;

import lombok.RequiredArgsConstructor;

//CommentController.java 예시

@Controller
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

 private final CommentService commentService;
 private final PostService postService;

 // 댓글 수정 폼 페이지 (GET)
 @GetMapping("/{commentId}/edit")
 public String editForm(@PathVariable Integer postId, @PathVariable Integer commentId, Model model) {
     Comment comment = commentService.getCommentById(commentId);
     model.addAttribute("comment", comment);
     model.addAttribute("postId", postId);
     return "comment/comment_edit";  // 아래에서 만들 html 파일명
 }

 // 댓글 수정 처리 (POST)
 @PostMapping("/{commentId}/edit")
 public String edit(@PathVariable Integer postId, @PathVariable Integer commentId,
                    @RequestParam String content) {
     commentService.updateComment(commentId, content);
     return "redirect:/posts/" + postId;
 }

 // 댓글 삭제 처리 (POST)
 @PostMapping("/{commentId}/delete")
 public String delete(@PathVariable Integer postId, @PathVariable Integer commentId) {
     commentService.deleteComment(commentId);
     return "redirect:/posts/" + postId;
 }
}
