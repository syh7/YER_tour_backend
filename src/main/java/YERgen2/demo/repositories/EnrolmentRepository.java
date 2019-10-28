package YERgen2.demo.repositories;

import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Enrolment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface EnrolmentRepository extends CrudRepository<Enrolment, Long> {

    Iterable<Enrolment> findByTournamentId(Long tournamentId);
    Iterable<Enrolment> findByTournamentIdAndDiscipline(Long tournamentId, Discipline discipline);
    Iterable<Enrolment> findByTournamentIdAndDisciplineAndPlayerLevel(Long tournamentId, Discipline discipline, int playerLevel);
    
}
