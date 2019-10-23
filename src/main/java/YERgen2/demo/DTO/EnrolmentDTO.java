package YERgen2.demo.DTO;

import YERgen2.demo.model.Discipline;

public class EnrolmentDTO  {

    private long id;
    private long partnerLeagueNumber;
    private int playerLevel;
    private Discipline discipline;
    private long tournamentId;

    public long getId() {
        return id;
    }
    public long getPartnerLeagueNumber() {
        return partnerLeagueNumber;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public long getTournamentId() {
        return tournamentId;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setPartnerLeagueNumber(long partnerLeagueNumber) {
        this.partnerLeagueNumber = partnerLeagueNumber;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
