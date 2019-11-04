package YERgen2.demo.model;

import YERgen2.demo.DTO.ParticipantDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Participant extends Account {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    private boolean male;
    @NotNull
    private int playerLevel;
    private int leagueNumber;
    @NotNull
    private LocalDate dateOfBirth;
    @ManyToMany
    private List<Enrolment> enrolments;
    @ManyToMany
    private List<Team> teams;

    public Participant(){
        this.teams = new ArrayList<>();
        this.enrolments = new ArrayList<>();
    }
    public Participant(@NotBlank String email, @NotBlank String password, @NotBlank String firstName,
                       @NotBlank String lastName, @NotNull boolean male, @NotNull int playerLevel,
                       @NotNull LocalDate dateOfBirth) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.male = male;
        this.playerLevel = playerLevel;
        this.dateOfBirth = dateOfBirth;
        this.teams = new ArrayList<>();
        this.enrolments = new ArrayList<>();
    }
    public Participant(@NotBlank String email, @NotBlank String password, @NotBlank String firstName,
                       @NotBlank String lastName, @NotNull boolean male, @NotNull int playerLevel, int leagueNumber,
                       @NotNull LocalDate dateOfBirth, List<Enrolment> enrolments, List<Team> teams) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.male = male;
        this.playerLevel = playerLevel;
        this.leagueNumber = leagueNumber;
        this.dateOfBirth = dateOfBirth;
        this.enrolments = enrolments;
        this.teams = teams;
    }
    public Participant(ParticipantDTO participantDTO, String password, List<Enrolment> enrolments, List<Team> teams){
        super(participantDTO.getId(), participantDTO.getEmail(), password);
        this.firstName = participantDTO.getFirstName();
        this.lastName = participantDTO.getLastName();
        this.male = participantDTO.isMale();
        this.playerLevel = participantDTO.getPlayerLevel();
        this.leagueNumber = participantDTO.getLeagueNumber();
        this.dateOfBirth = participantDTO.getDateOfBirth();
        this.enrolments = enrolments;
        this.teams = teams;
    }
    public Participant(Participant copyParticipant){
        super(copyParticipant.getId(), copyParticipant.getEmail(), copyParticipant.getPassword());
        this.firstName = copyParticipant.getFirstName();
        this.lastName = copyParticipant.getLastName();
        this.male = copyParticipant.isMale();
        this.playerLevel = copyParticipant.getPlayerLevel();
        this.leagueNumber = copyParticipant.getLeagueNumber();
        this.dateOfBirth = copyParticipant.getDateOfBirth();
        this.enrolments = copyParticipant.getEnrolments();
        this.teams = copyParticipant.getTeams();
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
    public boolean isMale() {
        return male;
    }
    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public List<Enrolment> getEnrolments() {
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
    public void setMale(boolean isMale) {
        this.male = isMale;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setEnrolments(List<Enrolment> enrolments) {
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

    public int getNumberEnrolmentsInTournament(long tournamentId){
        int count = 0;
        for(Enrolment enrolment : enrolments){
            if(enrolment.getTournament().getId() == tournamentId){
                count++;
            }
        }
        return count;
    }

    public boolean addEnrolment(Enrolment enrolment){
        return enrolments.add(enrolment);
    }
    public boolean updateEnrolment(Enrolment newEnrolment){
        for(Enrolment enrolment : enrolments){
            if(enrolment.getId() == newEnrolment.getId()){
                enrolments.remove(enrolment);
                return enrolments.add(newEnrolment);
            }
        }
        return false;
    }
    public boolean removeEnrolment(Enrolment enrolment){
        return enrolments.remove(enrolment);
    }
    public void emptyEnrolments(){
        enrolments.clear();
    }

    public boolean addTeam(Team team){
        return teams.add(team);
    }
    public boolean updateTeam(Team newTeam){
        for(Team team : teams){
            if(team.getId() == newTeam.getId()){
                teams.remove(team);
                return teams.add(newTeam);
            }
        }
        return false;
    }
    public boolean removeTeam(Team team){
        return teams.remove(team);
    }
    public void emptyTeams(){
        enrolments.clear();
    }

}
