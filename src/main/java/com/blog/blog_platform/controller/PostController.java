package com.blog.blog_platform.controller;

import com.blog.blog_platform.entity.Post;
import com.blog.blog_platform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam String title,
                                           @RequestParam String content,
                                           Principal principal) {
        return ResponseEntity.ok(postService.createPost(title, content, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id,
                                           @RequestParam String title,
                                           @RequestParam String content,
                                           Principal principal) {
        return ResponseEntity.ok(postService.updatePost(id, title, content, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id,
                                             Principal principal) {
        postService.deletePost(id, principal.getName());
        return ResponseEntity.ok("Post deleted successfully");
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.likePost(id));
    }
}