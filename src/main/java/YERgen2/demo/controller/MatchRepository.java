package YERgen2.demo.controller;

import YERgen2.demo.model.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MatchRepository extends CrudRepository<Match, Long> {
}
