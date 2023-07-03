package com.example.EasyContentManager.controller;

import com.example.EasyContentManager.model.ArticleVersion;
import com.example.EasyContentManager.service.ArticleVersionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/articleVersions")
public class ArticleVersionController {
    private final ArticleVersionService articleVersionService;

    public ArticleVersionController(ArticleVersionService articleVersionService) {
        this.articleVersionService = articleVersionService;
    }

    @GetMapping
    public List<ArticleVersion> getAllArticleVersions() {
        return articleVersionService.getAllArticleVersions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleVersion> getArticleVersionById(@PathVariable(value = "id") Long id) {
        ArticleVersion articleVersion = articleVersionService.getArticleVersionById(id);
        return ResponseEntity.ok().body(articleVersion);
    }

    @PostMapping
    public ArticleVersion createArticleVersion(@Valid @RequestBody ArticleVersion articleVersion) {
        return articleVersionService.createArticleVersion(articleVersion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleVersion> updateArticleVersion(@PathVariable(value = "id") Long id,
                                                               @Valid @RequestBody ArticleVersion articleVersionDetails) {
        ArticleVersion updatedArticleVersion = articleVersionService.updateArticleVersion(id, articleVersionDetails);
        return ResponseEntity.ok(updatedArticleVersion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticleVersion(@PathVariable(value = "id") Long id) {
        articleVersionService.deleteArticleVersion(id);
        return ResponseEntity.ok().build();
    }
}
