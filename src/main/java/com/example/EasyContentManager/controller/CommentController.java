package com.example.EasyContentManager.controller;

import com.example.EasyContentManager.model.Comment;
import com.example.EasyContentManager.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok().body(comment);
    }

    @PostMapping
    public Comment createComment(@Valid @RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody Comment commentDetails) {
        Comment updatedComment = commentService.updateComment(id, commentDetails);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }


    // CommentController.java

    @PostMapping("/articles/{articleId}/comments")
    public Comment createCommentForArticle(@PathVariable(value = "articleId") Long articleId, @Valid @RequestBody Comment comment) {
        return commentService.createCommentForArticle(articleId, comment);
    }

    @GetMapping("/articles/{articleId}/comments")
    public List<Comment> getCommentsByArticle(@PathVariable(value = "articleId") Long articleId) {
        return commentService.getCommentsByArticle(articleId);
    }

}
