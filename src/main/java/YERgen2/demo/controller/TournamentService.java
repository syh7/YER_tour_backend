package YERgen2.demo.controller;

import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    TournamentService(TournamentRepository tournamentRepository){
        this.tournamentRepository = tournamentRepository;
    }

    public Tournament save(Tournament participant){
        return tournamentRepository.save(participant);
    }

    public Optional<Tournament> findById(Long id){
        return tournamentRepository.findById(id);
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

}
