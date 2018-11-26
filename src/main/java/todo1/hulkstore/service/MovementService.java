package todo1.hulkstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import todo1.hulkstore.controller.request.MovementRequest;
import todo1.hulkstore.model.Movement;

@Service
public interface MovementService {

  List<Movement> getMovements();

  void createMovement(MovementRequest req);

  List<Movement> getMovements(String articleCode);

}
