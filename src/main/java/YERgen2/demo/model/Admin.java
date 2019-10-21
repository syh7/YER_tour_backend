package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Admin extends Account {

    private String name;
    @OneToMany
    private List<Tournament> tournament;

    public String getName() {
        return name;
    }
    public List<Tournament> getTournament() {
        return tournament;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTournament(List<Tournament> tournament) {
        this.tournament = tournament;
    }

}
