package YERgen2.demo.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Team> teams;
    private Stage stage;
    private String result;
    private Discipline discipline;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String judge;

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
    public String getResult() {
        return result;
    }
    public Stage getStage() {
        return stage;
    }
    public List<Team> getTeams() {
        return teams;
    }
    public String getJudge() {
        return judge;
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
    public void setResult(String result) {
        this.result = result;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setJudge(String judge) {
        this.judge = judge;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
