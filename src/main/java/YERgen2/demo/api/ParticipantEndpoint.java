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
    Participant newParticipant(@RequestBody Participant newParticipant) {
        return participantService.save(newParticipant);
    }

    @GetMapping(value="/participants")
    public List<Participant> getAllParticipants(){
        return (List<Participant>) participantService.findAll();
    }

    @GetMapping(value = "participants/{id}", produces = "application/json")
    public Participant getParticipant(@PathVariable long id) {
        return participantService.findById(id)
                .orElseThrow(() -> new ParticipantNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Participant replaceEmployee(@RequestBody Participant newParticipant, @PathVariable Long id) {

        return participantService.findById(id)
                .map(participant -> {
                    participant.setFirstName(newParticipant.getFirstName());
                    participant.setLastName(newParticipant.getLastName());
                    participant.setDateOfBirth(newParticipant.getDateOfBirth());
                    participant.setPlayerLevel(newParticipant.getPlayerLevel());
                    participant.setEmail(newParticipant.getEmail());
                    participant.setPassword(newParticipant.getPassword());
                    return participantService.save(participant);
                })
                .orElseGet(() -> {
                    newParticipant.setId(id);
                    return participantService.save(newParticipant);
                });
    }

    @DeleteMapping("/participants/{id}")
    void deleteParticipant(@PathVariable Long id) {
        participantService.deleteById(id);
    }

}
