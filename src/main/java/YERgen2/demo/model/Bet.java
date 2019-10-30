package YERgen2.demo.model;

import javax.persistence.*;

@Entity
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double amount;
    @ManyToOne
    private Bettor bettor;
    @ManyToOne
    private Game game;

    public Bet(){

    }

    public Bet(double amount, Bettor bettor, Game game){
        this.amount = amount;
        this.bettor = bettor;
        this.game = game;
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

    public String toString(){
        return bettor.getUserName() + " had bet " + amount + " on " + game.getTeamA() + " versus " + game.getTeamB();
    }
}
