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
    public Participant updateParticipant(@RequestBody Participant newParticipant, @PathVariable long id) {
        return participantService.updateParticipant(id, newParticipant);
    }

    @DeleteMapping("/participants/{id}")
    public void deleteParticipant(@PathVariable long id) {
        participantService.deleteById(id);
    }

}
