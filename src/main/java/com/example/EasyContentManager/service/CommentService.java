package com.example.EasyContentManager.service;

import com.example.EasyContentManager.exception.ResourceNotFoundException;
import com.example.EasyContentManager.model.Comment;
import com.example.EasyContentManager.repository.ArticleRepository;
import com.example.EasyContentManager.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id :" + commentId));
    }

    public Comment updateComment(Long commentId, Comment commentRequest) {
        return commentRepository.findById(commentId).map(comment -> {
            comment.setContent(commentRequest.getContent());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id :" + commentId));
    }

    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found with id :" + commentId);
        }
        commentRepository.deleteById(commentId);
    }

    // CommentService.java

    public Comment createCommentForArticle(Long articleId, Comment comment) {
        return articleRepository.findById(articleId).map(article -> {
            comment.setArticle(article);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Article id " +articleId));
    }

    public List<Comment> getCommentsByArticle(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

}
