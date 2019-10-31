package YERgen2.demo.repositories;

import YERgen2.demo.model.Bettor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BettorRepository extends CrudRepository<Bettor, Long> {

}
