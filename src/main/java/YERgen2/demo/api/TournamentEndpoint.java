package YERgen2.demo.api;

import YERgen2.demo.DTO.*;
import YERgen2.demo.controller.TournamentService;
import YERgen2.demo.model.Game;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
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
    public List<Tournament> getAllTournaments(@RequestParam(value = "mode") String mode, @RequestParam(value = "search") String search){
        if(mode.equals("contains")){
            return (List<Tournament>) tournamentService.findTournamentByNameContaining(search);
        } else {
            return (List<Tournament>) tournamentService.findAllTournament();
        }
    }

    /////TOURNAMENTS/ID

    @GetMapping(value = "/tournaments/{id}", produces = "application/json")
    public Tournament getTournament(@PathVariable long id) {
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
    public Game addGameToTournament(@PathVariable long id, @RequestBody Game game){
        return tournamentService.saveGame(id, game);
    }

}


