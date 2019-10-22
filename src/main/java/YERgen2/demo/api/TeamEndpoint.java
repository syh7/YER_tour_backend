package YERgen2.demo.api;

import YERgen2.demo.Exceptions.TeamNotFoundException;
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
        return teamService.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    @PutMapping("/teams/{id}")
    public Team replaceTeam(@RequestBody Team newTeam, @PathVariable Long id) {
        return teamService.findById(id)
                .map(team -> {
                    team.setDiscipline(newTeam.getDiscipline());
                    team.setPlayerLevel(newTeam.getPlayerLevel());
                    team.setGame(newTeam.getGame());
                    team.setTournament(newTeam.getTournament());
                    return teamService.save(team);
                })
                .orElseGet(() -> {
                    newTeam.setId(id);
                    return teamService.save(newTeam);
                });
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteById(id);
    }

}
