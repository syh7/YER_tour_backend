package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("2")
public class Participant extends Account {

    private String firstName;
    private String lastName;
    private int playerLevel;
    private int leagueNumber;
    private LocalDate dateOfBirth;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Enrolment enrolment;

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
    public Enrolment getEnrolment() {
        return enrolment;
    }
    public Team getTeam() {
        return team;
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
    public void setEnrolment(Enrolment enrolment) {
        this.enrolment = enrolment;
    }
    public void setTeam(Team team) {
        this.team = team;
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

}
