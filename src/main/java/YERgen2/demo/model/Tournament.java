package YERgen2.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String referee;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate enrolDate;
    @OneToMany
    private List<Match> matches;
    @OneToMany
    private List<Enrolment> enrolments;
    @OneToMany
    private List<Team> teams;
    private int[] maxDisciplines;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setReferee(String referee) {
        this.referee = referee;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setEnrolDate(LocalDate enrolDate) {
        this.enrolDate = enrolDate;
    }
    public List<Enrolment> getEnrolments() {
        return enrolments;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    public List<Team> getTeams() {
        return teams;
    }
    public void setMaxDisciplines(int[] maxDisciplines) {
        this.maxDisciplines = maxDisciplines;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReferee() {
        return referee;
    }
    public String getLocation() {
        return location;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDate getEnrolDate() {
        return enrolDate;
    }
    public List<Match> getMatches() {
        return matches;
    }
    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    public int[] getMaxDisciplines() {
        return maxDisciplines;
    }

    public boolean enrol(Enrolment enrolment){
        return this.enrolments.add(enrolment);
    }


}
