package YERgen2.demo.model;

public class Result {

    private int playerLevel;
    private Discipline discipline;
    private Team winners;
    private Team losers;

    public Result(int playerLevel, Discipline discipline, Team winners, Team losers){
        this.playerLevel = playerLevel;
        this.discipline = discipline;
        this.winners = winners;
        this.losers = losers;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public Team getWinners() {
        return winners;
    }
    public Team getLosers() {
        return losers;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setWinners(Team winners) {
        this.winners = winners;
    }
    public void setLosers(Team losers) {
        this.losers = losers;
    }

}
