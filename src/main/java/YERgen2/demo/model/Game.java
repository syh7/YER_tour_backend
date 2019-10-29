package YERgen2.demo.model;

import YERgen2.demo.DTO.GameDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Stage stage;
    private int[][] result;
    @NotNull
    private Discipline discipline;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String judge;
    @NotNull
    @ManyToOne
    @JsonIgnore
    private Tournament tournament;
    @NotNull
    @ManyToMany
    private List<Team> teams;

    public Game() {
        teams = new ArrayList<>();
    }
    public Game(@NotNull Stage stage, @NotNull Discipline discipline, @NotNull Tournament tournament,
                @NotNull List<Team> teams) {
        this.stage = stage;
        this.discipline = discipline;
        this.tournament = tournament;
        this.teams = teams;
    }
    public Game(long id, @NotNull Stage stage, int[][] result, @NotNull Discipline discipline, LocalTime startTime,
                LocalTime endTime, String location, String judge, @NotNull Tournament tournament,
                @NotNull List<Team> teams) {
        this.id = id;
        this.stage = stage;
        this.result = result;
        this.discipline = discipline;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.judge = judge;
        this.tournament = tournament;
        this.teams = teams;
    }
    public Game(GameDTO gameDTO, Tournament tournament, List<Team> teams){
        id = gameDTO.getId();
        stage = gameDTO.getStage();
        result = gameDTO.getResult();
        discipline = gameDTO.getDiscipline();
        startTime = gameDTO.getStartTime();
        endTime = gameDTO.getEndTime();
        location = gameDTO.getLocation();
        judge = gameDTO.getJudge();
        this.tournament = tournament;
        this.teams = teams;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public int[][] getResult() {
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
    public List<Team> getTeams() {
        return teams;
    }

    public long getId() {
        return id;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setResult(int[][] result) {
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
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
