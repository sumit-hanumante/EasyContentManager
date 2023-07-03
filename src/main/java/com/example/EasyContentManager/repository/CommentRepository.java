// CommentRepository.java
package com.example.EasyContentManager.repository;

import com.example.EasyContentManager.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // CommentRepository.java

    List<Comment> findByArticleId(Long articleId);

}