package todo1.hulkstore.model.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import todo1.hulkstore.controller.dto.MovementDto;
import todo1.hulkstore.model.Movement;

@Component
public class MovementMapper {

  @Autowired
  ArticleMapper articleMapper;

  private Function<Movement, MovementDto> createDto = movement -> {
    MovementDto dto = new MovementDto();
    dto.setId(movement.getId());
    dto.setDate(movement.getDate());
    dto.setQuantityInput(movement.getQuantityInput());
    dto.setQuantityOutput(movement.getQuantityOutput());
    return dto;
  };

  public MovementDto toDto(Movement movement) {
    return Optional.ofNullable(movement).map(createDto).orElse(null);
  }

  public List<MovementDto> toListDto(List<Movement> list) {
    return Optional.ofNullable(list).orElse(Collections.emptyList()).stream().map(createDto)
        .collect(Collectors.toList());
  }

}
