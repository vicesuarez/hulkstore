package todo1.hulkstore.controller.dto;

import lombok.Data;

@Data
public class ArticleDto {

  private String code;
  private String description;
  private Double price;
  private Integer quantity;

}
