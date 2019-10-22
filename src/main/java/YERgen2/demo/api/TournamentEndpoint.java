package YERgen2.demo.api;

import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.controller.EnrolmentService;
import YERgen2.demo.controller.TournamentService;
import YERgen2.demo.model.Enrolment;
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

    //tournaments/?mode=name&search=name
    @GetMapping(value="/tournaments")
    public List<Tournament> getAllTournaments(@RequestParam(value = "mode") String mode, @RequestParam(value = "search") String search){
        if(mode.equals("name")) {
            return (List<Tournament>) tournamentService.findByName(search);
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
    public Tournament replaceTournament(@RequestBody Tournament newTournament, @PathVariable long id) {
        try{
            Tournament tournament = tournamentService.findById(id);
            tournament.setName(newTournament.getName());
            tournament.setStartDate(newTournament.getStartDate());
            tournament.setEndDate(newTournament.getEndDate());
            tournament.setEnrolDate(newTournament.getEnrolDate());
            tournament.setReferee(newTournament.getReferee());
            tournament.setLocation(newTournament.getLocation());
            tournament.setMaxDisciplines(newTournament.getMaxDisciplines());
            tournament.setCategories(newTournament.getCategories());
            tournament.setAdmin(newTournament.getAdmin());
            return tournamentService.save(tournament);
        } catch (TournamentNotFoundException ex){
            newTournament.setId(id);
            return tournamentService.save(newTournament);
        }
    }

    @DeleteMapping("/tournaments/{id}")
    public void deleteTournament(@PathVariable long id) {
        tournamentService.deleteById(id);
    }

    /////TOURNAMENTS/ID/ENROLL

    @PostMapping("/tournaments/{id}/enroll")
    public Enrolment enrol(@PathVariable long id, @RequestBody Enrolment enrolment){
        return enrolmentService.save(id, enrolment);
    }

}
