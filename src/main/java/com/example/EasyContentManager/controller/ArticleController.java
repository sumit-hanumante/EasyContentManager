package com.example.EasyContentManager.controller;

import com.example.EasyContentManager.model.Article;
import com.example.EasyContentManager.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@Valid @RequestBody Article article) {
        return ResponseEntity.ok(articleService.createArticle(article));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long articleId) {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long articleId, @Valid @RequestBody Article articleRequest) {
        return ResponseEntity.ok(articleService.updateArticle(articleId, articleRequest));
    }

    @DeleteMapping("/{articleId}")
    @PreAuthorize("hasRole('ADMIN') or @articleService.getArticleById(#articleId).getAuthor().getUsername() == authentication.principal.username")
    public ResponseEntity<?> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok("Article successfully deleted");
    }



    // ArticleController.java

    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String query) {
        return ResponseEntity.ok(articleService.searchArticles(query));
    }

    // ArticleController.java

    @PostMapping("/{articleId}/versions/{versionId}/revert")
    public ResponseEntity<Article> revertArticleToVersion(@PathVariable Long articleId, @PathVariable Long versionId) {
        return ResponseEntity.ok(articleService.revertArticleToVersion(articleId, versionId));
    }


}
