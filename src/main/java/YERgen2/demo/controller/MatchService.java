package YERgen2.demo.controller;

import YERgen2.demo.model.Match;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    MatchService(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    public Match save(Match participant){
        return matchRepository.save(participant);
    }

    public Optional<Match> findById(Long id){
        return matchRepository.findById(id);
    }

    public Iterable <Match> findAll(){
        return matchRepository.findAll();
    }

    public void deleteById(Long id) {
        matchRepository.deleteById(id);
    }

}
