package YERgen2.demo.repositories;

import YERgen2.demo.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface BetRepository extends CrudRepository<Bet, Long> {
}
