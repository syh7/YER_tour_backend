package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Admin extends Account {

    @OneToMany
    private Set<Tournament> tournament;

    public Set<Tournament> getTournament() {
        return tournament;
    }
    public void setTournament(Set<Tournament> tournament) {
        this.tournament = tournament;
    }

}
