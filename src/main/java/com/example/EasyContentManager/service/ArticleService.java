package com.example.EasyContentManager.service;

import com.example.EasyContentManager.exception.ResourceNotFoundException;
import com.example.EasyContentManager.model.Article;
import com.example.EasyContentManager.model.ArticleVersion;
import com.example.EasyContentManager.repository.ArticleRepository;
import com.example.EasyContentManager.repository.ArticleVersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleVersionRepository articleVersionRepository;

    public ArticleService(ArticleRepository articleRepository , ArticleVersionRepository articleVersionRepository ) {
        this.articleRepository = articleRepository;
        this.articleVersionRepository = articleVersionRepository;
    }

    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article"));
    }

    public Article updateArticle(Long id, Article articleRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article id" + id));

        ArticleVersion articleVersion = new ArticleVersion();
        articleVersion.setTitle(article.getTitle());
        articleVersion.setContent(article.getContent());
        articleVersion.setArticle(article);

        articleVersionRepository.save(articleVersion);

        article.setTitle(articleRequest.getTitle());
        article.setContent(articleRequest.getContent());

        return articleRepository.save(article);
    }


    public void deleteArticle(Long id) {
        Article article = getArticleById(id);
        articleRepository.delete(article);
    }

    // ArticleService.java

    public List<Article> searchArticles(String query) {
        return articleRepository.findByTitleContaining(query);
    }
    // ArticleService.java

    public Article revertArticleToVersion(Long articleId, Long versionId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article id" +articleId));

        ArticleVersion articleVersion = articleVersionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("ArticleVersion id"+ versionId));

        article.setTitle(articleVersion.getTitle());
        article.setContent(articleVersion.getContent());

        return articleRepository.save(article);
    }


}
