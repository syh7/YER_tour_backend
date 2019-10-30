package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("3")
public class Bettor extends Account {

    @NotBlank
    private String userName;
    private double wallet;
    @OneToMany
    private List<Bet> bets;

    public Bettor(){
        this.bets = new ArrayList<Bet>();
    }

    public Bettor(@NotBlank String userName, @NotBlank String email, @NotBlank String password){
        super(email, password);
        this.userName = userName;
    }

    public Bettor(@NotBlank String userName, @NotBlank String email, @NotBlank String password, double wallet){
        super(email, password);
        this.userName = userName;
        this.wallet = wallet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
