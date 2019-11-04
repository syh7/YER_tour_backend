package YERgen2.demo.DTO;

import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Team;

import java.time.LocalDate;
import java.util.List;

public class ParticipantDTO {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean male;
    private int playerLevel;
    private int leagueNumber;
    private LocalDate dateOfBirth;
    private long[] enrolmentIds;
    private long[] teamIds;

    public ParticipantDTO(){}
    public ParticipantDTO(Participant participant){
        id = participant.getId();
        email = participant.getEmail();
        firstName = participant.getFirstName();
        lastName = participant.getLastName();
        playerLevel = participant.getPlayerLevel();
        leagueNumber = participant.getLeagueNumber();
        dateOfBirth = participant.getDateOfBirth();
        List<Enrolment> enrolments = participant.getEnrolments();
        enrolmentIds = new long[enrolments.size()];
        for(int i = 0; i < enrolments.size(); i++){
            enrolmentIds[i] = enrolments.get(i).getId();
        }
        List<Team> teams = participant.getTeams();
        teamIds = new long[teams.size()];
        for(int i = 0; i < teams.size(); i++){
            teamIds[i] = teams.get(i).getId();
        }
        male = participant.isMale();
    }

    public long getId(){
        return id;
    }
    public String getEmail() {
        return email;
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
    public long[] getEnrolments() {
        return enrolmentIds;
    }
    public long[] getTeams() {
        return teamIds;
    }
    public boolean isMale() {
        return male;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public void setEnrolments(long[] enrolments) {
        this.enrolmentIds = enrolments;
    }
    public void setTeams(long[] teams) {
        this.teamIds = teams;
    }
    public void setMale(boolean male) {
        this.male = male;
    }

}
