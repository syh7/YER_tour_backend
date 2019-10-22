package YERgen2.demo.api;

import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import YERgen2.demo.controller.ParticipantService;
import YERgen2.demo.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ParticipantEndpoint {

    @Autowired
    private ParticipantService participantService;

    @PostMapping("/participants")
    public Participant newParticipant(@RequestBody Participant newParticipant) {
        return participantService.save(newParticipant);
    }

    @GetMapping(value="/participants")
    public List<Participant> getAllParticipants(){
        return (List<Participant>) participantService.findAll();
    }

    @GetMapping(value = "participants/{id}", produces = "application/json")
    public Participant getParticipant(@PathVariable long id) {
        return participantService.findById(id);
    }

    @PutMapping("/participants/{id}")
    public Participant replaceParticipant(@RequestBody Participant newParticipant, @PathVariable long id) {
        try{
            Participant participant = participantService.findById(id);
            participant.setEmail(newParticipant.getEmail());
            participant.setPassword(newParticipant.getPassword());
            participant.setFirstName(newParticipant.getFirstName());
            participant.setLastName(newParticipant.getLastName());
            participant.setDateOfBirth(newParticipant.getDateOfBirth());
            participant.setPlayerLevel(newParticipant.getPlayerLevel());
            participant.setLeagueNumber(newParticipant.getLeagueNumber());
            participant.setEnrolment(newParticipant.getEnrolment());
            participant.setTeam(newParticipant.getTeam());
            return participantService.save(participant);
        } catch (ParticipantNotFoundException ex){
            newParticipant.setId(id);
            return participantService.save(newParticipant);
        }
    }

    @DeleteMapping("/participants/{id}")
    public void deleteParticipant(@PathVariable long id) {
        participantService.deleteById(id);
    }

}
