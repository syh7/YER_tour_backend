package YERgen2.demo.model;

public class Result {
    private int playerLevel;
    private Team winners;
    private Team losers;

    public Result(){}
    public Result(int playerLevel, Team winners, Team losers){
        this.playerLevel = playerLevel;
        this.winners = winners;
        this.losers = losers;
    }

    public int getPlayerLevel() {
        return playerLevel;
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
    public void setWinners(Team winners) {
        this.winners = winners;
    }
    public void setLosers(Team losers) {
        this.losers = losers;
    }

}
