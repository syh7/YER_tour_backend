package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.GameNotFoundException;
import YERgen2.demo.model.Game;
import YERgen2.demo.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GameService {

    @Autowired
    private GameRepository gameRepository;

    GameService(GameRepository matchRepository){
        this.gameRepository = matchRepository;
    }

    public Game save(Game participant){
        return gameRepository.save(participant);
    }

    public Game findById(Long id){
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

    public Game updateGame(long id, Game newGame){
        return gameRepository.findById(id).map(game -> {
            game.setJudge(newGame.getJudge());
            game.setStage(newGame.getStage());
            game.setLocation(newGame.getLocation());
            game.setDiscipline(newGame.getDiscipline());
            game.setStartTime(newGame.getStartTime());
            game.setEndTime(newGame.getEndTime());
            game.setResult(newGame.getResult());
            game.setTournament(newGame.getTournament());
            return gameRepository.save(game);
        }).orElseThrow(() -> new GameNotFoundException(newGame.getId()));
    }

    public Iterable <Game> findAll(){
        return gameRepository.findAll();
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

}
