package YERgen2.demo.controller;

import YERgen2.demo.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TeamRepository extends CrudRepository<Team, Long> {
}
