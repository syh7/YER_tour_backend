package YERgen2.demo.model;

import javax.persistence.*;

@Entity
public class Bet implements Comparable<Bet>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double amount;
    private double odds = 2;
    @ManyToOne
    private Bettor bettor;
    @ManyToOne
    private Game game;
    @ManyToOne
    private Team team;

    public Bet(){

    }

    public Bet(double amount, Bettor bettor, Game game, Team team){
        this.amount = amount;
        this.bettor = bettor;
        this.game = game;
        this.team = team;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getOdds() {
        return odds;
    }

    public void setOdds(double odds) {
        this.odds = odds;
    }

    public Bettor getBettor() {
        return bettor;
    }

    public void setBettor(Bettor bettor) {
        this.bettor = bettor;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String toString(){
        return bettor.getUserName() + " has bet " + amount + " on " + game.getTeamA() + " versus " + game.getTeamB() +
                "and thinks " + team + " will win";
    }

    @Override
    public int compareTo(Bet o) {
        return (int) (amount - o.amount);
    }
}
