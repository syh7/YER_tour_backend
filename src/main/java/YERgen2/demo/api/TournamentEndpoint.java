package YERgen2.demo.api;

import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.controller.EnrolmentService;
import YERgen2.demo.controller.TournamentService;
import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TournamentEndpoint {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private EnrolmentService enrolmentService;

    /////TOURNAMENTS

    @PostMapping("/tournaments")
    public Tournament newTournament(@RequestBody Tournament newAdmin) {
        return tournamentService.save(newAdmin);
    }

    //tournaments/?mode=foo&search=bar
    @GetMapping(value="/tournaments")
    public List<Tournament> getAllTournaments(@RequestParam(value = "mode") String mode, @RequestParam(value = "search") String search){
        if(mode.equals("exact")) {
            return (List<Tournament>) tournamentService.findByName(search);
        } else if(mode.equals("contains")){
            return (List<Tournament>) tournamentService.findByNameContaining(search);
        } else {
            return (List<Tournament>) tournamentService.findAll();
        }
    }

    /////TOURNAMENTS/ID

    @GetMapping(value = "/tournaments/{id}", produces = "application/json")
    public Tournament getTournament(@PathVariable long id) {
        return tournamentService.findById(id);
    }

    @PutMapping("/tournaments/{id}")
    public Tournament updateTournament(@RequestBody Tournament newTournament, @PathVariable long id) {
        return tournamentService.updateTournament(id, newTournament);
    }

    @DeleteMapping("/tournaments/{id}")
    public void deleteTournament(@PathVariable long id) {
        tournamentService.deleteById(id);
    }

    /////TOURNAMENTS/ID/ENROLL

    @PostMapping("/tournaments/{id}/enroll")
    public Enrolment enrol(@PathVariable long id, @RequestBody Enrolment enrolment, @RequestBody Participant participant){
        return enrolmentService.save(id, enrolment, participant);
    }

}
