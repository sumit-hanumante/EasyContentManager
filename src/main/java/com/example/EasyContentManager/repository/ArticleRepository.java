package com.example.EasyContentManager.repository;

import com.example.EasyContentManager.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // ArticleRepository.java

    List<Article> findByTitleContaining(String query);

}
