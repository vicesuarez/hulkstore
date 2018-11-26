package todo1.hulkstore.model.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import todo1.hulkstore.controller.dto.ArticleDto;
import todo1.hulkstore.model.Article;

@Component
public class ArticleMapper {

  private Function<Article, ArticleDto> createDto = article -> {
    ArticleDto dto = new ArticleDto();
    dto.setCode(article.getCode());
    dto.setDescription(article.getDescription());
    dto.setPrice(article.getPrice());
    dto.setQuantity(article.getQuantity());
    return dto;
  };

  private Function<ArticleDto, Article> createArticle = dto -> {
    Article article = new Article();
    article.setCode(dto.getCode());
    article.setDescription(dto.getDescription());
    article.setPrice(dto.getPrice());
    article.setQuantity(dto.getQuantity());
    return article;
  };

  public ArticleDto toDto(Article article) {
    return Optional.ofNullable(article).map(createDto).orElse(null);
  }

  public Article toArticle(ArticleDto dto) {
    return Optional.ofNullable(dto).map(createArticle).orElse(null);
  }

  public List<ArticleDto> toListDto(List<Article> list) {
    return Optional.ofNullable(list).orElse(Collections.emptyList()).stream().map(createDto)
        .collect(Collectors.toList());
  }

  public List<Article> toListArticle(List<ArticleDto> list) {
    return Optional.ofNullable(list).orElse(Collections.emptyList()).stream().map(createArticle)
        .collect(Collectors.toList());
  }

}
