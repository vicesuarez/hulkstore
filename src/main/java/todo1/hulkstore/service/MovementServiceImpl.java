package todo1.hulkstore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo1.hulkstore.controller.request.MovementRequest;
import todo1.hulkstore.model.Article;
import todo1.hulkstore.model.Movement;
import todo1.hulkstore.repository.MovementRepository;

@Service
public class MovementServiceImpl implements MovementService {

  @Autowired
  private ArticleService articleService;

  @Autowired
  private MovementRepository movementRepository;

  private Movement createNewMovement(MovementRequest req) {
    Movement movement = new Movement();
    movement.setDate(LocalDateTime.now());
    movement.setQuantityInput(req.getQuantityInput());
    movement.setQuantityOutput(req.getQuantityOutput());
    movement.setArticle(articleService.getArticle(req.getArticleCode()));
    return movement;
  }

  @Override
  public List<Movement> getMovements() {
    Iterable<Movement> movements = movementRepository.findAll();
    return StreamSupport.stream(movements.spliterator(), false).collect(Collectors.toList());
  }

  @Override
  public void createMovement(MovementRequest req) {
    Movement movement = createNewMovement(req);
    Article article = movement.getArticle();
    Integer articleQuantity = article.getQuantity();
    Integer movementQuantity = movement.getQuantityInput() - movement.getQuantityOutput();

    int newQuantity = articleQuantity + movementQuantity;
    if (newQuantity < 0) {
      throw new RuntimeException(String.format("There are only %s articles.", article.getQuantity()));
    }

    article.setQuantity(newQuantity);
    movementRepository.save(movement);
    articleService.updateQuantity(article.getCode(), newQuantity);
  }

  @Override
  public List<Movement> getMovements(String articleCode) {
    return movementRepository.findByArticleCode(articleCode);
  }

}
