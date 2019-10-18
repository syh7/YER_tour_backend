package YERgen2.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToMany
    private List<Team> teams;
    private String stage;
    private String result;
    private String discipline;
    private String startTime;
    private String endTime;
    private String location;

    public String getLocation() {
        return location;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getDiscipline() {
        return discipline;
    }
    public String getResult() {
        return result;
    }
    public String getStage() {
        return stage;
    }
    public List<Team> getTeams() {
        return teams;
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
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
