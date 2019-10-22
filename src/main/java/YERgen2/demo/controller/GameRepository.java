package YERgen2.demo.controller;

import YERgen2.demo.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface GameRepository extends CrudRepository<Game, Long> {
}
