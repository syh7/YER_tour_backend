package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.TeamNotFoundException;
import YERgen2.demo.model.Team;
import YERgen2.demo.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Team save(Team participant){
        return teamRepository.save(participant);
    }

    public Team findById(Long id){
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    public Team updateTeam(long id, Team newTeam){
        return teamRepository.findById(id).map(team -> {
            team.setDiscipline(newTeam.getDiscipline());
            team.setPlayerLevel(newTeam.getPlayerLevel());
            team.setGame(newTeam.getGame());
            team.setTournament(newTeam.getTournament());
            return teamRepository.save(team);
        }).orElseThrow(() -> new TeamNotFoundException(id));
    }

    public Iterable <Team> findAll(){
        return teamRepository.findAll();
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }
}
