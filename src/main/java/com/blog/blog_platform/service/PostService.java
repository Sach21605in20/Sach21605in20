package com.blog.blog_platform.service;

import com.blog.blog_platform.entity.*;
import com.blog.blog_platform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(String title, String content, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(user);
        post.setLikes(0);

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post updatePost(Long id, String title, String content, String username) {
        Post post = getPostById(id);

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    public void deletePost(Long id, String username) {
        Post post = getPostById(id);

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }

        postRepository.delete(post);
    }

    public Post likePost(Long id) {
        Post post = getPostById(id);
        post.setLikes(post.getLikes() + 1);
        return postRepository.save(post);
    }
}