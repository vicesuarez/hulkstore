package todo1.hulkstore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "movement")
public class Movement {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private LocalDateTime date;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "article_code")
  private Article article;

  @Column(name = "quantity_input")
  private Integer quantityInput;

  @Column(name = "quantity_output")
  private Integer quantityOutput;

}
