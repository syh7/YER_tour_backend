package YERgen2.demo.controller;

import YERgen2.demo.model.Game;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class GameService {

    @Autowired
    private GameRepository gameRepository;

    GameService(GameRepository matchRepository){
        this.gameRepository = matchRepository;
    }

    public Game save(Game participant){
        return gameRepository.save(participant);
    }

    public Optional<Game> findById(Long id){
        return gameRepository.findById(id);
    }

    public Iterable <Game> findAll(){
        return gameRepository.findAll();
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

}
