package com.blog.blog_platform.controller;

import com.blog.blog_platform.entity.Comment;
import com.blog.blog_platform.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @RequestParam String content,
                                              Principal principal) {
        return ResponseEntity.ok(commentService.addComment(postId, content, principal.getName()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id,
                                                Principal principal) {
        commentService.deleteComment(id, principal.getName());
        return ResponseEntity.ok("Comment deleted successfully");
    }
}