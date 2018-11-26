

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import todo1.hulkstore.HulkstoreApplication;
import todo1.hulkstore.controller.request.MovementRequest;
import todo1.hulkstore.service.ArticleService;
import todo1.hulkstore.service.MovementService;

@SpringBootTest(classes = HulkstoreApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class MovementsServiceTests {

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Autowired
  ArticleService articleService;
  @Autowired
  MovementService movementService;

  @Test
  public void autowiredTest() {
    assertNotNull(articleService);
    assertNotNull(movementService);
  }

  @Test
  public void movementsTest() {
    assertEquals(0, movementService.getMovements().size());
    assertEquals(Integer.valueOf(15), articleService.getArticle("2").getQuantity());

    MovementRequest req = new MovementRequest();
    req.setArticleCode("2");
    req.setQuantityInput(0);
    req.setQuantityOutput(5);
    movementService.createMovement(req);
    assertEquals(Integer.valueOf(10), articleService.getArticle("2").getQuantity());

    assertEquals(1, movementService.getMovements().size());
    assertEquals(0, movementService.getMovements("1").size());
    assertEquals(1, movementService.getMovements("2").size());
  }

  @Test
  public void createMovementNoArticles() {
    expectedEx.expect(RuntimeException.class);
    expectedEx.expectMessage("There are only 10 articles.");

    MovementRequest req = new MovementRequest();
    req.setArticleCode("1");
    req.setQuantityInput(0);
    req.setQuantityOutput(11);
    movementService.createMovement(req);
  }

  @Test
  public void createMovementArticleNotFound() {
    expectedEx.expect(RuntimeException.class);
    expectedEx.expectMessage("Article code 'x' not found.");

    MovementRequest req = new MovementRequest();
    req.setArticleCode("x");
    req.setQuantityInput(1);
    req.setQuantityOutput(1);
    movementService.createMovement(req);
  }

}
