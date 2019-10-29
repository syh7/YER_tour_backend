package YERgen2.demo.DTO;

import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Game;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Team;

import java.util.List;

public class TeamDTO {

    private long id;
    private int playerLevel;
    private Discipline discipline;
    private long[] gameIds;
    private long tournamentId;
    private long[] participantIds;

    public TeamDTO(){}
    public TeamDTO(Team team){
        id = team.getId();
        playerLevel = team.getPlayerLevel();
        discipline = team.getDiscipline();
        tournamentId = team.getTournament().getId();
        List<Participant> participants = team.getParticipants();
        participantIds = new long[participants.size()];
        for(int i = 0; i < participants.size(); i++){
            participantIds[i] = participants.get(i).getId();
        }
        List<Game> games = team.getGames();
        gameIds = new long[games.size()];
        for(int i = 0; i < games.size(); i++){
            gameIds[i] = games.get(i).getId();
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
    public long[] getGameIds() {
        return gameIds;
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
    public void setGameIds(long[] gameIds) {
        this.gameIds = gameIds;
    }
    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }
    public void setParticipantIds(long[] participantIds) {
        this.participantIds = participantIds;
    }

}
