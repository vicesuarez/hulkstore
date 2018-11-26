

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
import todo1.hulkstore.model.Article;
import todo1.hulkstore.service.ArticleService;

@SpringBootTest(classes = HulkstoreApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ArticlesServiceTests {

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Autowired
  ArticleService articleService;

  @Test
  public void autowiredTest() {
    assertNotNull(articleService);
  }

  @Test
  public void articlesTest() {
    assertEquals(2, articleService.getArticles().size());

    Article article = new Article();
    String newCode = "3";
    article.setCode(newCode);
    article.setDescription("Article3");
    article.setPrice(1.0);
    article.setQuantity(1);
    articleService.createArticle(article);
    assertEquals(3, articleService.getArticles().size());

    article = new Article();
    String newDescriptioin = "Article3-edited";
    article.setDescription(newDescriptioin);
    articleService.editArticle("3", article);
    assertEquals(newDescriptioin, articleService.getArticle(newCode).getDescription());
    Integer newQuantity = 11;
    articleService.updateQuantity("3", newQuantity);
    assertEquals(newQuantity, articleService.getArticle(newCode).getQuantity());

    articleService.removeArticle(newCode);
    assertEquals(2, articleService.getArticles().size());
  }

  @Test
  public void createArticleCodeExists() {
    expectedEx.expect(RuntimeException.class);
    expectedEx.expectMessage("Article code '1' already exists.");

    Article article = new Article();
    article.setCode("1");
    articleService.createArticle(article);
  }

  @Test
  public void getArticleNotFound() {
    expectedEx.expect(RuntimeException.class);
    expectedEx.expectMessage("Article code 'x' not found.");
    articleService.getArticle("x");
  }

  @Test
  public void editArticleNotFound() {
    expectedEx.expect(RuntimeException.class);
    expectedEx.expectMessage("Article code 'x' not found.");
    articleService.editArticle("x", new Article());
  }

}
