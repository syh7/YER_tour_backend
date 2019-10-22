package YERgen2.demo.controller;

import YERgen2.demo.model.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    List<Tournament> findByName(String name);
    List<Tournament> findByNameContaining(String name);

}
