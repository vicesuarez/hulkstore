package todo1.hulkstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import todo1.hulkstore.model.Movement;

@Component
public interface MovementRepository extends CrudRepository<Movement, Long> {

  @Query("FROM Movement m WHERE m.article.code = :articleCode")
  List<Movement> findByArticleCode(@Param("articleCode") String articleCode);

}
