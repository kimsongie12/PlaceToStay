package com.example.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Post;
import com.example.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(Integer id) {
        return postRepository.findById(id).orElseThrow();
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
    
    public Page<Post> searchPosts(String keyword, Pageable pageable) {
        return postRepository.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(keyword, pageable);
    }
    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
    public Page<Post> searchPostsByTitle(String keyword, Pageable pageable) {
        return postRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    }


}
