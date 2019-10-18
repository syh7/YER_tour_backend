package YERgen2.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToMany
    private List<Participant> teamMembers;


    public List<Participant> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<Participant> teamMembers) {
        this.teamMembers = teamMembers;
    }

}
