package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Admin extends Account {

    private String name;
    @OneToMany
    private Set<Tournament> tournament;

    public String getName() {
        return name;
    }
    public Set<Tournament> getTournament() {
        return tournament;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTournament(Set<Tournament> tournament) {
        this.tournament = tournament;
    }

}
