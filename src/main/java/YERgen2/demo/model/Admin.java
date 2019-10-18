package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Admin extends Account {

    private List<Tournament> tournament;

    public List<Tournament> getTournament() {
        return tournament;
    }

    public void setTournament(List<Tournament> tournament) {
        this.tournament = tournament;
    }
}
