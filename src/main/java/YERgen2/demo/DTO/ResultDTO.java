package YERgen2.demo.DTO;

import YERgen2.demo.model.Result;

public class ResultDTO {
    private int playerLevel;
    private long winTeamId;
    private long loseTeamId;

    public ResultDTO(Result result){
        playerLevel = result.getPlayerLevel();
        winTeamId = result.getWinners().getId();
        loseTeamId = result.getLosers().getId();
    }

    public int getPlayerLevel() {
        return playerLevel;
    }
    public long getWinTeamId() {
        return winTeamId;
    }
    public long getLoseTeamId() {
        return loseTeamId;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setWinTeamId(long winTeamId) {
        this.winTeamId = winTeamId;
    }
    public void setLoseTeamId(long loseTeamId) {
        this.loseTeamId = loseTeamId;
    }
}
