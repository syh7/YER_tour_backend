package YERgen2.demo.model;

import javax.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int playerLevel;
    private Discipline discipline;
    @ManyToOne
    private Team winners;
    @ManyToOne
    private Team losers;

    public Result(){}
    public Result(int playerLevel, Discipline discipline, Team winners, Team losers){
        this.playerLevel = playerLevel;
        this.discipline = discipline;
        this.winners = winners;
        this.losers = losers;
    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
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
