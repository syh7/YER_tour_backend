package YERgen2.demo.DTO;

import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Game;
import YERgen2.demo.model.Stage;

import java.time.LocalDateTime;

public class GameDTO {
    private long id;
    private Stage stage;
    private ResultDTO resultDTO;
    private Discipline discipline;
    private int playerLevel;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String judge;
    private long tournamentId;
    private long teamAId;
    private long teamBId;
    private int[][] score;

    public GameDTO(Game game){
        id = game.getId();
        stage = game.getStage();
        if(game.getResult() != null){
            resultDTO = new ResultDTO(game.getResult());
        } else {
            resultDTO = null;
        }
        discipline = game.getDiscipline();
        playerLevel = game.getPlayerLevel();
        startTime = game.getStartTime();
        endTime = game.getEndTime();
        location = game.getLocation();
        judge = game.getJudge();
        tournamentId = game.getTournament().getId();
        teamAId = game.getTeamA().getId();
        teamBId = game.getTeamB().getId();
    }

    public long getId() {
        return id;
    }
    public Stage getStage() {
        return stage;
    }
    public ResultDTO getResultDTO() {
        return resultDTO;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public String getLocation() {
        return location;
    }
    public String getJudge() {
        return judge;
    }
    public long getTournamentId() {
        return tournamentId;
    }
    public long getTeamAId() {
        return teamAId;
    }
    public long getTeamBId() {
        return teamBId;
    }
    public int[][] getScore() {
        return score;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setResultDTO(ResultDTO result) {
        this.resultDTO = result;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setJudge(String judge) {
        this.judge = judge;
    }
    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }
    public void setTeamAId(long teamAId) {
        this.teamAId = teamAId;
    }
    public void setTeamBId(long teamBId) {
        this.teamBId = teamBId;
    }
    public void setScore(int[][] score) {
        this.score = score;
    }

}
