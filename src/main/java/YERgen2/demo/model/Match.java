package YERgen2.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private Set<Team> teams;
    private Stage stage;
    private String result;
    private Discipline discipline;
    private String startTime;
    private String endTime;
    private String location;

    public void setId(long id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getStartTime() {
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
    public Set<Team> getTeams() {
        return teams;
    }

    public long getId() {
        return id;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setStartTime(String startTime) {
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
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
