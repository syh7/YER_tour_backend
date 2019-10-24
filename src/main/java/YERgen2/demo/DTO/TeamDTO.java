package YERgen2.demo.DTO;

import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Team;

public class TeamDTO {

    private long id;
    private int playerLevel;
    private Discipline discipline;
    private long gameId;
    private long tournamentId;

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

    public TeamDTO(){}
    public TeamDTO(Team team){
        id = team.getId();
        playerLevel = team.getPlayerLevel();
        discipline = team.getDiscipline();
        gameId = team.getGame().getId();
        tournamentId = team.getTournament().getId();
    }

}
