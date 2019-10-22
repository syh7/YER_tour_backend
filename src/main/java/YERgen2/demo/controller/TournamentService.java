package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    TournamentService(TournamentRepository tournamentRepository){
        this.tournamentRepository = tournamentRepository;
    }

    public Tournament save(Tournament tournament){
        return tournamentRepository.save(tournament);
    }

    public Tournament findById(Long id){
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public boolean existsById(Long id){
        return tournamentRepository.existsById(id);
    }

    public Iterable<Tournament> findAll(){
        return tournamentRepository.findAll();
    }

    public void deleteById(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Iterable<Tournament> findByName(String name){
        return tournamentRepository.findByName(name);
    }

    public Iterable<Tournament> findByNameContaining(String name){
        return tournamentRepository.findByNameContaining(name);
    }

}
