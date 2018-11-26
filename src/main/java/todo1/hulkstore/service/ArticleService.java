package todo1.hulkstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import todo1.hulkstore.model.Article;

@Service
public interface ArticleService {

  List<Article> getArticles();

  void createArticle(Article article);

  Article getArticle(String code);

  void editArticle(String code, Article article);

  void removeArticle(String code);

  void updateQuantity(String code, Integer newQuantity);

}
