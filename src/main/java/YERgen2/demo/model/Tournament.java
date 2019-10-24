package YERgen2.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;
    private String description;
    private String referee;
    private String location;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private LocalDate enrolDate;
    private int maxDisciplines;
    private int[] levels;
    @NotNull
    @ManyToOne
    @JsonIgnore
    private Admin admin;
    @OneToMany(mappedBy = "tournament")
    private List<Enrolment> enrolments;
    @OneToMany(mappedBy = "tournament")
    private List<Team> teams;

    public Tournament(){
        enrolments = new ArrayList<>();
        teams = new ArrayList<>();
    }
    public Tournament(@NotNull String name, @NotNull LocalDate startDate, @NotNull LocalDate endDate, @NotNull Admin admin) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.admin = admin;
        //filler data
        this.description = "description";
        this.referee = "referee";
        this.location = "location";
        this.enrolDate = LocalDate.now();
        this.maxDisciplines = 1;
        this.levels = new int[]{1, 2, 3};
        enrolments = new ArrayList<>();
        teams = new ArrayList<>();
    }
    public Tournament(@NotNull String name, String description, String referee, String location, @NotNull LocalDate startDate,
                      @NotNull LocalDate endDate, LocalDate enrolDate, int maxDisciplines, int[] levels, @NotNull Admin admin,
                      List<Enrolment> enrolments, List<Team> teams) {
        this.name = name;
        this.description = description;
        this.referee = referee;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrolDate = enrolDate;
        this.maxDisciplines = maxDisciplines;
        this.levels = levels;
        this.admin = admin;
        this.enrolments = enrolments;
        this.teams = teams;
    }
    public Tournament(Tournament newTournament){
        id = newTournament.getId();
        name = newTournament.getName();
        description = newTournament.getDescription();
        referee = newTournament.getReferee();
        location = newTournament.getLocation();
        startDate = newTournament.getStartDate();
        endDate = newTournament.getEndDate();
        enrolDate = newTournament.getEnrolDate();
        maxDisciplines = newTournament.getMaxDisciplines();
        levels = newTournament.getLevels();
        admin = newTournament.getAdmin();
        enrolments = newTournament.getEnrolments();
        teams = newTournament.getTeams();
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
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
    public int getMaxDisciplines() {
        return maxDisciplines;
    }
    public int[] getLevels() {
        return levels;
    }
    public Admin getAdmin() {
        return admin;
    }
    public List<Enrolment> getEnrolments() {
        return enrolments;
    }
    public List<Team> getTeams() {
        return teams;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public void setMaxDisciplines(int maxDisciplines) {
        this.maxDisciplines = maxDisciplines;
    }
    public void setLevels(int[] levels) {
        this.levels = levels;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public boolean addEnrolment(Enrolment enrolment){
        return enrolments.add(enrolment);
    }

}
