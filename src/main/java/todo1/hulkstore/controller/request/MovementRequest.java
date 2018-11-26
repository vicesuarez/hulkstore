package todo1.hulkstore.controller.request;

import lombok.Data;

@Data
public class MovementRequest {

  private String articleCode;
  private Integer quantityInput;
  private Integer quantityOutput;

}
