package YERgen2.demo.DTO;

import YERgen2.demo.model.Bet;

public class BetDTO {
    private long id;
    private double amount;
    private long bettorId;
    private long gameId;
    private long teamId;

    public BetDTO(){}
    public BetDTO(Bet bet){
        this.id = bet.getId();
        this.amount = bet.getAmount();
        this.bettorId = bet.getBettor().getId();
        this.gameId = bet.getGame().getId();
        this.teamId = bet.getTeam().getId();
    }

    public long getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public long getBettorId() {
        return bettorId;
    }
    public long getGameId() {
        return gameId;
    }
    public long getTeamId() {
        return teamId;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setBettorId(long bettorId) {
        this.bettorId = bettorId;
    }
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

}
