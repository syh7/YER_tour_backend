package YERgen2.demo.api;

import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.controller.TournamentService;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TournamentEndpoint {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/tournaments")
    public Tournament newTournament(@RequestBody Tournament newAdmin) {
        return tournamentService.save(newAdmin);
    }

    @GetMapping(value="/tournaments")
    public List<Tournament> getAllTournaments(){
        return (List<Tournament>) tournamentService.findAll();
    }

    @GetMapping(value = "tournaments/{id}", produces = "application/json")
    public Tournament getTournament(@PathVariable long id) {
        return tournamentService.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    @PutMapping("/tournaments/{id}")
    public Tournament replaceTournament(@RequestBody Tournament newTournament, @PathVariable Long id) {
        return tournamentService.findById(id)
                .map(tournament -> {
                    tournament.setName(newTournament.getName());
                    tournament.setJudge(newTournament.getJudge());
                    tournament.setReferee(newTournament.getReferee());
                    tournament.setLocation(newTournament.getLocation());
                    tournament.setMatches(newTournament.getMatches());
                    return tournamentService.save(tournament);
                })
                .orElseGet(() -> {
                    newTournament.setId(id);
                    return tournamentService.save(newTournament);
                });
    }

    @DeleteMapping("/tournaments/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteById(id);
    }

}
