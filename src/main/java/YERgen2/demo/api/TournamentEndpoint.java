package YERgen2.demo.api;

import YERgen2.demo.DTO.*;
import YERgen2.demo.controller.TournamentService;
import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TournamentEndpoint {

    @Autowired
    private TournamentService tournamentService;

    /////TOURNAMENTS

    @PostMapping("/tournaments")
    public Tournament newTournament(@RequestBody NewTournamentWrapper newTournamentWrapper) {
        return tournamentService.saveTournament(newTournamentWrapper);
    }

    //tournaments/?mode=foo&search=bar
    @GetMapping(value="/tournaments")
    public List<TournamentDTO> getAllTournaments(@RequestParam(value = "mode", defaultValue = "all") String mode,
                                                 @RequestParam(value = "search", defaultValue = "") String search){
        if(mode.equals("contains")){
            return (List<TournamentDTO>) tournamentService.findTournamentByNameContaining(search);
        } else {
            return (List<TournamentDTO>) tournamentService.findAllTournament();
        }
    }

    /////TOURNAMENTS/ID

    @GetMapping(value = "/tournaments/{id}", produces = "application/json")
    public TournamentDTO getTournament(@PathVariable long id) {
        return tournamentService.findTournamentById(id);
    }

    @PutMapping("/tournaments/{id}")
    public Tournament updateTournament(@RequestBody Tournament newTournament, @PathVariable long id) {
        return tournamentService.updateTournament(id, newTournament);
    }

    @DeleteMapping("/tournaments/{id}")
    public void deleteTournament(@PathVariable long id) {
        tournamentService.deleteTournamentById(id);
    }

    /////TOURNAMENTS/ID/ENROLL

    @PostMapping("/tournaments/{id}/enrol")
    public ParticipantDTO enrol(@PathVariable long id, @RequestBody NewEnrolmentWrapper newEnrolmentWrapper){
        return tournamentService.enrolParticipantInTournament(id, newEnrolmentWrapper);
    }

    @GetMapping("/tournaments/{id}/enrol")
    public List<EnrolmentDTO> getAllEnrolments(@PathVariable long id){
        return tournamentService.findEnrolmentByTournamentId(id);
    }

    @PutMapping("/tournaments/{id}/enrol")
    public List<EnrolmentDTO> updateEnrolments(@PathVariable long id, @RequestBody NewEnrolmentWrapper newEnrolmentWrapper){
        return tournamentService.updateEnrolments(newEnrolmentWrapper);
    }

    /////TOURNAMENTS/ID/TEAMS

    @GetMapping("/tournaments/{id}/teams")
    public List<EnrolmentDTO> getEnrolmentByTournament(@PathVariable long id){
        return (List<EnrolmentDTO>) tournamentService.findEnrolmentByTournamentId(id);
    }

    /////TOURNAMENTS/ID/TEAMS/DISCIPLINE

    @GetMapping("/tournaments/{id}/teams/{discipline}")
    public List<EnrolmentDTO> getEnrolmentByDiscipline(@PathVariable long id, @PathVariable int discipline){
        return (List<EnrolmentDTO>) tournamentService.findEnrolmentByTournamentIdAndDiscipline(id, discipline);
    }

    /////TOURNAMENTS/ID/GAMES

    @GetMapping("/tournaments/{id}/games")
    public List<GameDTO> getGamesByTournamentId(@PathVariable long id){
        return (List<GameDTO>) tournamentService.findGamesByTournamentId(id);
    }

    @PostMapping("/tournaments/{id}/games")
    public List<GameDTO> addGameToTournament(@PathVariable long id, @RequestBody List<GameDTO> gameDTOs){
        return tournamentService.saveGames(id, gameDTOs);
    }

    @PutMapping("/tournaments/{id}/games/")
    public GameDTO updateGame(@PathVariable long id, @RequestBody GameDTO gameDTO){
        return tournamentService.updateGame(gameDTO);
    }

    /////TOURNAMENT/ID/RESULTS

    @GetMapping("/tournaments/{id}/results/all")
    public List<ResultDTO> getTournamentResults(@PathVariable long id){
        return tournamentService.getTournamentResults(id);
    }

    //tournament/id/results/?discipline=DISCIPLINE&playerlevel=int
    @GetMapping("/tournaments/{id}/results")
    public ResultDTO getTournamentResult(@PathVariable long id, @RequestParam(value = "discipline") Discipline discipline,
                                         @RequestParam(value = "playerlevel") int playerLevel){
        return tournamentService.getTournamentResultByDisciplineAndPlayerLevel(id, discipline, playerLevel);
    }

    //tournament/id/playerresults/playerid
    @GetMapping("/tournaments/{id}/playerresults/{playerid}")
    public List<Integer> getPlayerResult(@PathVariable long id, @PathVariable long playerid){
        return tournamentService.getTournamentResultByPlayerId(id, playerid);
    }

}


