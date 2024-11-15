package com.example.kiwoong.service;

import com.example.kiwoong.domain.Post;
import com.example.kiwoong.domain.User;
import com.example.kiwoong.dto.PostDto;
import com.example.kiwoong.repository.PostRepository;
import com.example.kiwoong.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;//이거 맞나 알아보기

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post createPost(PostDto postDto, User user) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user); // 현재 사용자 설정
        return postRepository.save(post);
    }

    // Read
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Post updatePost(Long id, PostDto postDto, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        // 게시글 작성자와 현재 사용자 비교
        if (!post.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id, User user) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        // 게시글 작성자와 현재 사용자 비교
        if (!post.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        postRepository.delete(post);
    }
}