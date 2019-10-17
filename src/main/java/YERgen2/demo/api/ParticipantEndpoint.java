package YERgen2.demo.api;

import YERgen2.demo.controller.ParticipantService;
import YERgen2.demo.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping
public class ParticipantEndpoint {

    @Autowired
    private ParticipantService participantService;

    @GetMapping(value = "participant/{id}", produces = "application/json")
    public Optional<Participant> getUsers(@PathVariable long id) {
        return participantService.findById(id);
    }

}
