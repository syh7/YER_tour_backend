package YERgen2.demo.DTO;

import YERgen2.demo.model.*;

import java.time.LocalDate;
import java.util.List;

public class TournamentDTO {

    private long id;
    private String name;
    private String description;
    private String referee;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate enrolDate;
    private int maxDisciplines;
    private int[] levels;
    private long adminId;
    private long[] enrolmentIds;
    private long[] teamIds;
    private long[] gameIds;
    private ResultDTO[] resultDTOs;

    public TournamentDTO(){}
    public TournamentDTO(Tournament tournament){
        id = tournament.getId();
        name = tournament.getName();
        description = tournament.getDescription();
        referee = tournament.getReferee();
        location = tournament.getLocation();
        startDate = tournament.getStartDate();
        endDate = tournament.getEndDate();
        enrolDate = tournament.getEnrolDate();
        maxDisciplines = tournament.getMaxDisciplines();
        levels = tournament.getLevels();
        adminId = tournament.getAdmin().getId();
        List<Enrolment> enrolments = tournament.getEnrolments();
        enrolmentIds = new long[enrolments.size()];
        for(int i = 0; i < enrolments.size(); i++){
            enrolmentIds[i] = enrolments.get(i).getId();
        }
        List<Team> teams = tournament.getTeams();
        teamIds = new long[teams.size()];
        for(int i = 0; i < teams.size(); i++){
            teamIds[i] = teams.get(i).getId();
        }
        List<Game> games = tournament.getGames();
        gameIds = new long[games.size()];
        for(int i = 0; i < games.size(); i++){
            gameIds[i] = games.get(i).getId();
        }
        List<Result> results = tournament.getResults();
        resultDTOs = new ResultDTO[results.size()];
        for(int i = 0; i < results.size(); i++){
            resultDTOs[i] = new ResultDTO(results.get(i));
        }
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
    public long getAdminId() {
        return adminId;
    }
    public long[] getEnrolmentIds() {
        return enrolmentIds;
    }
    public long[] getTeamIds() {
        return teamIds;
    }
    public long[] getGameIds() {
        return gameIds;
    }
    public ResultDTO[] getResultDTOs() {
        return resultDTOs;
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
    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
    public void setEnrolmentIds(long[] enrolmentIds) {
        this.enrolmentIds = enrolmentIds;
    }
    public void setTeamIds(long[] teamIds) {
        this.teamIds = teamIds;
    }
    public void setGameIds(long[] gameIds) {
        this.gameIds = gameIds;
    }
    public void setResultDTOs(ResultDTO[] resultDTOs) {
        this.resultDTOs = resultDTOs;
    }

}
