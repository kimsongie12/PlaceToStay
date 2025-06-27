package com.example.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Comment;
import com.example.entity.Member;
import com.example.entity.Post;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import com.example.service.MemberService;
import com.example.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	private final CommentService commentService;
	private final MemberService memberService;

	@GetMapping
	public String list(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(required = false) String keyword,  // 검색어 추가
	    Model model) {

	    Pageable pageable = PageRequest.of(page, 5, Sort.by("createdAt").descending());
	    Page<Post> posts;

	    if (keyword != null && !keyword.isEmpty()) {
	        posts = postService.searchPostsByTitle(keyword, pageable);
	    } else {
	        posts = postService.getPosts(pageable);
	    }

	    model.addAttribute("posts", posts);
	    model.addAttribute("keyword", keyword);  // 뷰에서 검색창에 유지시키기 위해
	    return "post/list";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("post", new Post());
		return "post/form";
	}

	@PostMapping
	public String create(@ModelAttribute Post post) {
		postService.savePost(post);
		return "redirect:/posts";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable Integer id, Model model) {
		Post post = postService.getPostById(id);
		model.addAttribute("post", post);

		model.addAttribute("comments", commentService.getCommentsByPost(post)); // 댓글 목록
		model.addAttribute("comment", new Comment()); // 댓글 작성 폼에 쓸 빈 객체

		return "post/detail";
	}

	@PostMapping("/{id}/comments")
	public String addComment(@PathVariable Integer id, @ModelAttribute Comment comment) {
		Post post = postService.getPostById(id);
		comment.setPost(post);

		// 로그인 사용자 정보 필요 – 현재는 더미 memberId로 대체
		Member member = memberService.getById(1); // 임시 로그인 사용자
		comment.setMember(member);

		commentService.saveComment(comment);
		return "redirect:/posts/" + id;
	}

	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable Integer id, Model model) {
		model.addAttribute("post", postService.getPostById(id));
		return "post/form";
	}

	@PostMapping("/{id}/edit")
	public String update(@PathVariable Integer id, @ModelAttribute Post updatedPost) {
		Post post = postService.getPostById(id);
		post.setTitle(updatedPost.getTitle());
		post.setContent(updatedPost.getContent());
		postService.savePost(post);
		return "redirect:/posts";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Integer id) {
		postService.deletePost(id);
		return "redirect:/posts";
	}

}
