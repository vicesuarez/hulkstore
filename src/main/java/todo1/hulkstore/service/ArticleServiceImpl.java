package todo1.hulkstore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo1.hulkstore.model.Article;
import todo1.hulkstore.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  @Override
  public List<Article> getArticles() {
    Iterable<Article> articles = articleRepository.findAll();
    return StreamSupport.stream(articles.spliterator(), false).collect(Collectors.toList());
  }

  @Override
  public void createArticle(Article article) {
    String code = Optional.ofNullable(article).map(Article::getCode).orElse("");
    if (articleRepository.existsById(code)) {
      throw new RuntimeException(String.format("Article code '%s' already exists.", code));
    }
    articleRepository.save(article);
  }

  @Override
  public Article getArticle(String code) {
    return articleRepository.findById(code)
        .orElseThrow(() -> new RuntimeException(String.format("Article code '%s' not found.", code)));
  }

  @Override
  public void editArticle(String code, Article article) {
    if (article == null) {
      return;
    }

    Article oldArticle = getArticle(code);
    String newCode = article.getCode();
    oldArticle.setCode(newCode != null ? newCode : code);
    String newDescription = article.getDescription();
    oldArticle.setDescription(newDescription != null ? newDescription : oldArticle.getDescription());
    Double newPrice = article.getPrice();
    oldArticle.setPrice(newPrice != null ? newPrice : oldArticle.getPrice());
    Integer newQuantity = article.getQuantity();
    oldArticle.setQuantity(newQuantity != null ? newQuantity : oldArticle.getQuantity());
    articleRepository.save(oldArticle);

    if (article.getCode() != null && !code.equals(article.getCode())) {
      removeArticle(code);
    }
  }

  @Override
  public void removeArticle(String code) {
    articleRepository.deleteById(code);
  }

  @Override
  public void updateQuantity(String code, Integer newQuantity) {
    articleRepository.updateQuantity(code, newQuantity);
  };

}
