package YERgen2.demo.api;

import YERgen2.demo.Exceptions.MatchNotFoundException;
import YERgen2.demo.controller.MatchService;
import YERgen2.demo.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MatchEndpoint {

    @Autowired
    private MatchService matchService;

    @PostMapping("/matches")
    public Match newMatch(@RequestBody Match newParticipant) {
        return matchService.save(newParticipant);
    }

    @GetMapping(value="/matches")
    public List<Match> getAllMatches(){
        return (List<Match>) matchService.findAll();
    }

    @GetMapping(value = "matches/{id}", produces = "application/json")
    public Match getMatch(@PathVariable long id) {
        return matchService.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(id));
    }

    @PutMapping("/matches/{id}")
    public Match replaceMatch(@RequestBody Match newMatch, @PathVariable Long id) {
        return matchService.findById(id)
                .map(match -> {
                    match.setJudge(newMatch.getJudge());
                    match.setStage(newMatch.getStage());
                    match.setTeams(newMatch.getTeams());
                    match.setLocation(newMatch.getLocation());
                    match.setDiscipline(newMatch.getDiscipline());
                    match.setStartTime(newMatch.getStartTime());
                    match.setEndTime(newMatch.getEndTime());
                    match.setResult(newMatch.getResult());
                    return matchService.save(match);
                })
                .orElseGet(() -> {
                    newMatch.setId(id);
                    return matchService.save(newMatch);
                });
    }

    @DeleteMapping("/matches/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchService.deleteById(id);
    }

}
