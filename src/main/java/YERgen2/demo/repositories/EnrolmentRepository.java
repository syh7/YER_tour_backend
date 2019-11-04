package YERgen2.demo.repositories;

import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Enrolment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface EnrolmentRepository extends CrudRepository<Enrolment, Long> {

    Iterable<Enrolment> findByTournamentId(Long tournamentId);

    /* Example for @Query statement instead of findByTournamentIdAndDiscipline
     * .@Query("SELECT e FROM Enrolment WHERE e.tournamentid = tournamentId AND e.discipline = discipline")
     * Iterable<Enrolment> findTournamentIdAndDiscipline(Long tournamentId, Discipline discipline)
     */
    Iterable<Enrolment> findByTournamentIdAndDiscipline(Long tournamentId, Discipline discipline);

}
