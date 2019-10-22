package YERgen2.demo.api;

import YERgen2.demo.Exceptions.MatchNotFoundException;
import YERgen2.demo.controller.GameService;
import YERgen2.demo.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GameEndpoint {

    @Autowired
    private GameService gameService;

    @PostMapping("/matches")
    public Game newGame(@RequestBody Game newGame) {
        return gameService.save(newGame);
    }

    @GetMapping(value="/matches")
    public List<Game> getAllGames(){
        return (List<Game>) gameService.findAll();
    }

    @GetMapping(value = "matches/{id}", produces = "application/json")
    public Game getGame(@PathVariable long id) {
        return gameService.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
    }

    @PutMapping("/matches/{id}")
    public Game replaceGame(@RequestBody Game newGame, @PathVariable Long id) {
        return gameService.findById(id)
                .map(game -> {
                    game.setJudge(newGame.getJudge());
                    game.setStage(newGame.getStage());
                    game.setLocation(newGame.getLocation());
                    game.setDiscipline(newGame.getDiscipline());
                    game.setStartTime(newGame.getStartTime());
                    game.setEndTime(newGame.getEndTime());
                    game.setResult(newGame.getResult());
                    game.setTournament(newGame.getTournament());
                    return gameService.save(game);
                })
                .orElseGet(() -> {
                    newGame.setId(id);
                    return gameService.save(newGame);
                });
    }

    @DeleteMapping("/matches/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteById(id);
    }

}
