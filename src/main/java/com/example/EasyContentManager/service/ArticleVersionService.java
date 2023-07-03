// ArticleVersionService.java
package com.example.EasyContentManager.service;

import com.example.EasyContentManager.exception.ResourceNotFoundException;
import com.example.EasyContentManager.model.ArticleVersion;
import com.example.EasyContentManager.repository.ArticleVersionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleVersionService {
    private final ArticleVersionRepository articleVersionRepository;

    public ArticleVersionService(ArticleVersionRepository articleVersionRepository) {
        this.articleVersionRepository = articleVersionRepository;
    }

    @Transactional(readOnly = true)
    public List<ArticleVersion> getAllArticleVersions() {
        return articleVersionRepository.findAll();
    }

    public ArticleVersion createArticleVersion(ArticleVersion articleVersion) {
        return articleVersionRepository.save(articleVersion);
    }

    public ArticleVersion getArticleVersionById(Long id) {
        return articleVersionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ArticleVersion not found with id: " + id));
    }

    public ArticleVersion updateArticleVersion(Long id, ArticleVersion articleVersionRequest) {
        return articleVersionRepository.findById(id).map(articleVersion -> {
            articleVersion.setContent(articleVersionRequest.getContent());
            return articleVersionRepository.save(articleVersion);
        }).orElseThrow(() -> new ResourceNotFoundException("ArticleVersion not found with id: " + id));
    }

    public void deleteArticleVersion(Long id) {
        if (!articleVersionRepository.existsById(id)) {
            throw new ResourceNotFoundException("ArticleVersion not found with id: " + id);
        }
        articleVersionRepository.deleteById(id);
    }
}
