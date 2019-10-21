package YERgen2.demo.api;

import YERgen2.demo.Exceptions.TournamentNotFoundException;
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

    /////TOURNAMENTS

    @PostMapping("/tournaments")
    public Tournament newTournament(@RequestBody Tournament newAdmin) {
        return tournamentService.save(newAdmin);
    }

    @GetMapping(value="/tournaments")
    public List<Tournament> getAllTournaments(){
        return (List<Tournament>) tournamentService.findAll();
    }

    /////TOURNAMENTS/ID

    @GetMapping(value = "tournaments/{id}", produces = "application/json")
    public Tournament getTournament(@PathVariable long id) {
        return tournamentService.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }

    @PutMapping("/tournaments/{id}")
    public Tournament replaceTournament(@RequestBody Tournament newTournament, @PathVariable long id) {
        return tournamentService.findById(id)
                .map(tournament -> {
                    tournament.setName(newTournament.getName());
                    tournament.setStartDate(newTournament.getStartDate());
                    tournament.setEndDate(newTournament.getEndDate());
                    tournament.setEnrolDate(newTournament.getEnrolDate());
                    tournament.setReferee(newTournament.getReferee());
                    tournament.setLocation(newTournament.getLocation());
                    tournament.setMatches(newTournament.getMatches());
                    tournament.setEnrolments(newTournament.getEnrolments());
                    tournament.setTeams(newTournament.getTeams());
                    tournament.setMaxDisciplines(newTournament.getMaxDisciplines());
                    return tournamentService.save(tournament);
                })
                .orElseGet(() -> {
                    newTournament.setId(id);
                    return tournamentService.save(newTournament);
                });
    }

    @DeleteMapping("/tournaments/{id}")
    public void deleteTournament(@PathVariable long id) {
        tournamentService.deleteById(id);
    }

    /////TOURNAMENTS/ID/ENROLL

    @PostMapping("/tournaments/{id}/enroll")
    public boolean enrollParticipant(@RequestBody Enrolment enrolment, @PathVariable long id){
        return tournamentService.enrol(id, enrolment);
    }

    @GetMapping("/tournaments/{id}/enroll")
    public List<Enrolment> getEnrolments(@RequestBody long participantId, @PathVariable long tournamentId){
        return (List<Enrolment>) tournamentService.getEnrolment(participantId, tournamentId);
    }

}
