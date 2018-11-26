package todo1.hulkstore.controller.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MovementDto {

  private Long id;
  private LocalDateTime date;
  private ArticleDto article;
  private Integer quantityInput;
  private Integer quantityOutput;

}
