package YERgen2.demo.api;

import YERgen2.demo.controller.TeamService;
import YERgen2.demo.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TeamEndpoint {

    @Autowired
    private TeamService teamService;

    @PostMapping("/teams")
    public Team newTeam(@RequestBody Team newParticipant) {
        return teamService.save(newParticipant);
    }

    @GetMapping(value="/teams")
    public List<Team> getAllTeams(){
        return (List<Team>) teamService.findAll();
    }

    @GetMapping(value = "teams/{id}", produces = "application/json")
    public Team getTeam(@PathVariable long id) {
        return teamService.findById(id);
    }

    @PutMapping("/teams/{id}")
    public Team replaceTeam(@RequestBody Team newTeam, @PathVariable long id) {
        return teamService.updateTeam(id, newTeam);
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable long id) {
        teamService.deleteById(id);
    }

}
