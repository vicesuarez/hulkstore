package todo1.hulkstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "article")
public class Article {

  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Double price;

  @Column(name = "quantity")
  private Integer quantity;

}
