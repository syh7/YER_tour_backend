package YERgen2.demo.DTO;

import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToMany;
import java.util.List;

public class TeamDTO {

    private long id;
    private int playerLevel;
    private Discipline discipline;
    private long gameId;
    private long tournamentId;
    private long[] participantIds;

    public TeamDTO(){}
    public TeamDTO(Team team){
        id = team.getId();
        playerLevel = team.getPlayerLevel();
        discipline = team.getDiscipline();
        gameId = team.getGame().getId();
        tournamentId = team.getTournament().getId();
        List<Participant> participants = team.getParticipants();
        participantIds = new long[participants.size()];
        for(int i = 0; i < participants.size(); i++){
            participantIds[i] = participants.get(i).getId();
        }
    }

    public long getId() {
        return id;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public long getGameId() {
        return gameId;
    }
    public long getTournamentId() {
        return tournamentId;
    }
    public long[] getParticipantIds() {
        return participantIds;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }
    public void setParticipantIds(long[] participantIds) {
        this.participantIds = participantIds;
    }

}
