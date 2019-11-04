package YERgen2.demo.api;

import YERgen2.demo.DTO.ParticipantDTO;
import YERgen2.demo.DTO.TournamentDTO;
import YERgen2.demo.controller.AccountService;
import YERgen2.demo.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ParticipantEndpoint {

    @Autowired
    private AccountService accountService;

    /////PARTICIPANTS

    @PostMapping("/participants")
    public Participant newParticipant(@RequestBody Participant newParticipant) {
        return accountService.saveParticipant(newParticipant);
    }

    @GetMapping(value="/participants")
    public List<ParticipantDTO> getAllParticipants(){
        return (List<ParticipantDTO>) accountService.findAllParticipant();
    }

    /////PARTICIPANTS/ID

    @GetMapping(value = "participants/{id}", produces = "application/json")
    public ParticipantDTO getParticipant(@PathVariable long id) {
        return accountService.findParticipantById(id);
    }

    @PutMapping("/participants/{id}/newpassword")
    public ParticipantDTO updateParticipant(@PathVariable long id, @RequestBody Participant newParticipant) {
        return accountService.updateParticipant(id, newParticipant);
    }
    @PutMapping("/participants/{id}")
    public ParticipantDTO updateParticipantDTO(@PathVariable long id, @RequestBody ParticipantDTO newParticipantDTO) {
        return accountService.updateParticipantDTO(id, newParticipantDTO);
    }

    @DeleteMapping("/participants/{id}")
    public void deleteParticipant(@PathVariable long id) {
        accountService.deleteParticipantById(id);
    }

    /////PARTICIPANTS/ID/TOURNAMENTS

    @GetMapping("/participants/{id}/tournaments")
    public Set<TournamentDTO> getTournaments(@PathVariable long id){
        return accountService.getParticipantTournaments(id);
    }

    /////PARTICIPANTS/ID/RESULTS

    @GetMapping("/participants/{id}/results")
    public int[] getAllResults(@PathVariable long id){
        return accountService.getAllResults(id);
    }

}
