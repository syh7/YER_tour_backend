package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Participant extends Account {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private int playerLevel;
    private int leagueNumber;
    @NotNull
    private LocalDate dateOfBirth;
    @OneToMany
    private List<Enrolment> enrolments;
    @ManyToMany
    private List<Team> teams;

    public Participant(){
        this.teams = new ArrayList<>();
        this.enrolments = new ArrayList<>();
    }
    public Participant(@NotNull String email, @NotNull String password, @NotNull String firstName,
                       @NotNull String lastName, @NotNull int playerLevel, @NotNull LocalDate dateOfBirth) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerLevel = playerLevel;
        this.dateOfBirth = dateOfBirth;
        this.teams = new ArrayList<>();
        this.enrolments = new ArrayList<>();
    }
    public Participant(@NotNull String email, @NotNull String password, @NotNull String firstName,
                       @NotNull String lastName, @NotNull int playerLevel, int leagueNumber,
                       @NotNull LocalDate dateOfBirth, List<Enrolment> enrolments, List<Team> teams) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerLevel = playerLevel;
        this.leagueNumber = leagueNumber;
        this.dateOfBirth = dateOfBirth;
        this.enrolments = enrolments;
        this.teams = teams;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getPlayerLevel(){
        return playerLevel;
    }
    public int getLeagueNumber() {
        return leagueNumber;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public List<Enrolment> getEnrolment() {
        return enrolments;
    }
    public List<Team> getTeams() {
        return teams;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setPlayerLevel(int playerLevel){
        this.playerLevel = playerLevel;
    }
    public void setLeagueNumber(int leagueNumber) {
        this.leagueNumber = leagueNumber;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setEnrolment(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString(){
        String str = "ID: " + getId() + "\n";
        str += "Name: " + firstName + " " + lastName + "\n";
        str += "Born on: " + dateOfBirth.toString() + "\n";
        str += "Playerlevel: " + playerLevel + "\n";
        str += "Email: " + getEmail();
        str += "Password: " + getPassword();
        return str;
    }

    public boolean addEnrolment(Enrolment enrolment){
        return this.enrolments.add(enrolment);
    }

    public int getNumberEnrolmentsInTournament(long tournamentId){
        int count = 0;
        for(Enrolment enrolment : enrolments){
            if(enrolment.getTournament().getId() == tournamentId){
                count++;
            }
        }
        return count;
    }

    public boolean deleteEnrolment(Enrolment enrolment){
        return this.enrolments.remove(enrolment);
    }

}
