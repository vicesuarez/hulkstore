package todo1.hulkstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import todo1.hulkstore.controller.dto.ArticleDto;
import todo1.hulkstore.controller.dto.ArticlesListDto;
import todo1.hulkstore.controller.dto.OkResponse;
import todo1.hulkstore.model.mapper.ArticleMapper;
import todo1.hulkstore.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @Autowired
  private ArticleMapper articleMapper;

  @ApiOperation("Get articles.")
  @RequestMapping(method = RequestMethod.GET)
  public ArticlesListDto getArticles() {
    List<ArticleDto> list = articleMapper.toListDto(articleService.getArticles());
    ArticlesListDto response = new ArticlesListDto();
    response.setArticles(list);
    return response;
  }

  @ApiOperation("Create articles.")
  @RequestMapping(method = RequestMethod.POST)
  public OkResponse createArticle(@RequestBody ArticleDto article) {
    articleService.createArticle(articleMapper.toArticle(article));
    return new OkResponse();
  }

  @ApiOperation("Get an article.")
  @RequestMapping(value = "/{code}", method = RequestMethod.GET)
  public ArticleDto getArticle(@PathVariable String code) throws NotFoundException {
    return articleMapper.toDto(articleService.getArticle(code));
  }

  @ApiOperation("Edit an article.")
  @RequestMapping(value = "/{code}", method = RequestMethod.PUT)
  public OkResponse editArticle(@PathVariable String code, @RequestBody ArticleDto article) {
    articleService.editArticle(code, articleMapper.toArticle(article));
    return new OkResponse();
  }

  @ApiOperation("Remove an article.")
  @RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
  public OkResponse removeArticle(@PathVariable String code) {
    articleService.removeArticle(code);
    return new OkResponse();
  }

}
