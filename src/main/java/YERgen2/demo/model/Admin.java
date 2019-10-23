package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Admin extends Account {


    @NotNull
    private String name;
    @OneToMany
    private List<Tournament> tournaments;

    public Admin(){
        this.tournaments = new ArrayList<>();
    }

    public Admin(@NotNull String name, @NotNull String email, @NotNull String password) {
        super(email, password);
        this.name = name;
        this.tournaments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public boolean addTournament(Tournament tournament){
        return this.tournaments.add(tournament);
    }

    public boolean removeTournament(Tournament tournament){
        return this.tournaments.remove(tournament);
    }

}
