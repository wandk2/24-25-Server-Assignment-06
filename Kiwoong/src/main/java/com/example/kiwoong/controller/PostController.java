package com.example.kiwoong.controller;

import com.example.kiwoong.domain.Post;
import com.example.kiwoong.domain.User;
import com.example.kiwoong.dto.PostDto;
import com.example.kiwoong.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto, @AuthenticationPrincipal User user) {
        Post createdPost = postService.createPost(postDto, user);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostDto postDto, @AuthenticationPrincipal User user) {
        Post updatedPost = postService.updatePost(id, postDto, user);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @AuthenticationPrincipal User user) {
        postService.deletePost(id, user);
        return ResponseEntity.noContent().build();
    }
}