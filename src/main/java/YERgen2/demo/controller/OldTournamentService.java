package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.model.Tournament;
import YERgen2.demo.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OldTournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    OldTournamentService(TournamentRepository tournamentRepository){
        this.tournamentRepository = tournamentRepository;
    }

    public Tournament save(Tournament tournament){
        return tournamentRepository.save(tournament);
    }

    public Tournament findById(long id){
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public Tournament updateTournament(long id, Tournament newTournament){
        return tournamentRepository.findById(id).map(tournament -> {
            tournament.setName(newTournament.getName());
            tournament.setDescription(newTournament.getDescription());
            tournament.setReferee(newTournament.getReferee());
            tournament.setLocation(newTournament.getLocation());
            tournament.setStartDate(newTournament.getStartDate());
            tournament.setEndDate(newTournament.getEndDate());
            tournament.setEnrolDate(newTournament.getEnrolDate());
            tournament.setMaxDisciplines(newTournament.getMaxDisciplines());
            tournament.setCategories(newTournament.getCategories());
            tournament.setAdmin(newTournament.getAdmin());
            return tournamentRepository.save(tournament);
        }).orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public boolean existsById(long id){
        return tournamentRepository.existsById(id);
    }

    public Iterable<Tournament> findAll(){
        return tournamentRepository.findAll();
    }

    public void deleteById(long id) {
        tournamentRepository.deleteById(id);
    }

    public Iterable<Tournament> findByNameContaining(String name){
        return tournamentRepository.findByNameContaining(name);
    }

}
