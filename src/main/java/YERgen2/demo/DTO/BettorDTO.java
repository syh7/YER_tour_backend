package YERgen2.demo.DTO;

import YERgen2.demo.model.Bet;
import YERgen2.demo.model.Bettor;

import java.util.List;

public class BettorDTO {
    private long id;
    private String email;
    private String userName;
    private double wallet;
    private long[] betIds;

    public BettorDTO(Bettor bettor){
        this.id = bettor.getId();
        this.email = bettor.getEmail();
        this.userName = bettor.getUserName();
        this.wallet = bettor.getWallet();
        List<Bet> bettors = bettor.getBets();
        betIds = new long[bettors.size()];
        for(int i = 0; i < betIds.length; i++){
            betIds[i] = bettors.get(i).getId();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long[] getBetIds() {
        return betIds;
    }

    public void setBetIds(long[] betIds) {
        this.betIds = betIds;
    }
}
