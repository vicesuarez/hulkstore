package todo1.hulkstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import todo1.hulkstore.controller.dto.MovementDto;
import todo1.hulkstore.controller.dto.MovementsListDto;
import todo1.hulkstore.controller.dto.OkResponse;
import todo1.hulkstore.controller.request.MovementRequest;
import todo1.hulkstore.model.mapper.MovementMapper;
import todo1.hulkstore.service.MovementService;

@RestController
@RequestMapping("/movements")
public class MovementController {

  @Autowired
  private MovementService movementService;

  @Autowired
  private MovementMapper movementMapper;

  @ApiOperation("Get movements.")
  @RequestMapping(method = RequestMethod.GET)
  public MovementsListDto getMovements() {
    List<MovementDto> list = movementMapper.toListDto(movementService.getMovements());
    MovementsListDto response = new MovementsListDto();
    response.setMovements(list);
    return response;
  }

  @ApiOperation("Create a new movement.")
  @RequestMapping(method = RequestMethod.POST)
  public OkResponse createMovement(@RequestBody MovementRequest req) {
    movementService.createMovement(req);
    return new OkResponse();
  }

  @ApiOperation("Get movements.")
  @RequestMapping(value = "/{articleCode}", method = RequestMethod.GET)
  public MovementsListDto getMovements(@PathVariable String articleCode) {
    List<MovementDto> list = movementMapper.toListDto(movementService.getMovements(articleCode));
    MovementsListDto response = new MovementsListDto();
    response.setMovements(list);
    return response;
  }

}
