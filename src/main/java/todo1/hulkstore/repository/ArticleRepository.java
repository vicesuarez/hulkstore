package todo1.hulkstore.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import todo1.hulkstore.model.Article;

@Component
public interface ArticleRepository extends CrudRepository<Article, String> {

  @Transactional
  @Modifying
  @Query("UPDATE Article a SET a.quantity = :newQuantity WHERE a.code = :code")
  void updateQuantity(@Param("code") String code, @Param("newQuantity") Integer newQuantity);

}
