package YERgen2.demo.api;

import YERgen2.demo.DTO.EnrolmentDTO;
import YERgen2.demo.DTO.NewTournamentWrapper;
import YERgen2.demo.DTO.ParticipantDTO;
import YERgen2.demo.controller.TournamentService;
import YERgen2.demo.DTO.NewEnrolmentWrapper;
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
        return (List<EnrolmentDTO>) tournamentService.findEnrolmentByTournamentId(id);
    }

    @PutMapping("/tournaments/{id}/enrol")
    public List<EnrolmentDTO> updateEnrolments(@PathVariable long id, @RequestBody NewEnrolmentWrapper newEnrolmentWrapper){
        return tournamentService.updateEnrolments(id, newEnrolmentWrapper);
    }

}


