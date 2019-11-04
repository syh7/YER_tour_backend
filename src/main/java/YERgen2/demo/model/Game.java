package YERgen2.demo.model;

import YERgen2.demo.DTO.GameDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Stage stage;
    @OneToOne
    private Result result;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Discipline discipline;
    @NotNull
    private int playerLevel;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String judge;
    @NotNull
    @ManyToOne
    private Tournament tournament;
    @NotNull
    @ManyToOne
    private Team teamA;
    @NotNull
    @ManyToOne
    private Team teamB;
    private int[][] score;
    @OneToMany(mappedBy = "game")
    private List<Bet> bets;

    public Game() {
        List<Bet> bets = new ArrayList<>();
    }
    public Game(@NotNull Stage stage, @NotNull Discipline discipline, @NotNull Tournament tournament,
                @NotNull Team teamA, @NotNull Team teamB, @NotNull int playerLevel) {
        this.stage = stage;
        this.discipline = discipline;
        this.playerLevel = playerLevel;
        this.tournament = tournament;
        this.teamA = teamA;
        this.teamB = teamB;
        this.bets = new ArrayList<>();
    }
    public Game(long id, @NotNull Stage stage, Result result, @NotNull Discipline discipline, LocalDateTime startTime,
                LocalDateTime endTime, String location, String judge, @NotNull Tournament tournament,
                @NotNull Team teamA, @NotNull Team teamB, @NotNull int playerLevel, int[][] score) {
        this.id = id;
        this.stage = stage;
        this.result = result;
        this.discipline = discipline;
        this.playerLevel = playerLevel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.judge = judge;
        this.tournament = tournament;
        this.teamA = teamA;
        this.teamB = teamB;
        this.score = score;
        this.bets = new ArrayList<>();
    }
    public Game(@NotNull GameDTO gameDTO, @NotNull Tournament tournament, @NotNull Team teamA, @NotNull Team teamB,
                Result result, List<Bet> bets){
        id = gameDTO.getId();
        stage = gameDTO.getStage();
        this.result = result;
        discipline = gameDTO.getDiscipline();
        playerLevel = gameDTO.getPlayerLevel();
        startTime = gameDTO.getStartTime();
        endTime = gameDTO.getEndTime();
        location = gameDTO.getLocation();
        judge = gameDTO.getJudge();
        this.tournament = tournament;
        this.teamA = teamA;
        this.teamB = teamB;
        this.score = gameDTO.getScore();
        this.bets = bets;
    }
    public Game(@NotNull GameDTO gameDTO, @NotNull Tournament tournament, @NotNull Team teamA, @NotNull Team teamB,
                List<Bet> bets){
        id = gameDTO.getId();
        stage = gameDTO.getStage();
        discipline = gameDTO.getDiscipline();
        playerLevel = gameDTO.getPlayerLevel();
        startTime = gameDTO.getStartTime();
        endTime = gameDTO.getEndTime();
        location = gameDTO.getLocation();
        judge = gameDTO.getJudge();
        this.tournament = tournament;
        this.teamA = teamA;
        this.teamB = teamB;
        this.score = gameDTO.getScore();
        this.bets = bets;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Result getResult() {
        return result;
    }
    public Stage getStage() {
        return stage;
    }
    public String getJudge() {
        return judge;
    }
    public Tournament getTournament() {
        return tournament;
    }
    public Team getTeamA() {
        return teamA;
    }
    public Team getTeamB() {
        return teamB;
    }
    public int[][] getScore() {
        return score;
    }
    public List<Bet> getBets() {
        return bets;
    }

    public long getId() {
        return id;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setResult(Result result) {
        this.result = result;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setJudge(String judge) {
        this.judge = judge;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }
    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }
    public void setScore(int[][] score) {
        this.score = score;
    }
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public Team getWinningTeam(){
        return result.getWinners();
    }
    public Team getLosingTeam(){
        return result.getLosers();
    }

    public Result finishGame(int[][] score){
        this.score = score;
        int a = 0, b = 0;
        for(int[] row : score){
            if(row[0] > row[1]){
                a++;
            } else {
                b++;
            }
        }
        if(a > b){
            System.out.println("made new result in game with playerlevel: " + playerLevel);
            result = new Result(playerLevel, discipline, teamA, teamB);
        } else {
            System.out.println("made new result in game with playerlevel: " + playerLevel);
            result = new Result(playerLevel, discipline, teamB, teamA);
        }
        return result;
    }

}
