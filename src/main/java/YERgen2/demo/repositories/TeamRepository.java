package YERgen2.demo.repositories;

import YERgen2.demo.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TeamRepository extends CrudRepository<Team, Long> {
}
