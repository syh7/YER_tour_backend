package YERgen2.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private Set<Participant> teamMembers;
    private int playerLevel;
    private Discipline discipline;

    public long getId(){
        return id;
    }
    public Set<Participant> getTeamMembers() {
        return teamMembers;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setId(long id){
        this.id = id;
    }
    public void setTeamMembers(Set<Participant> teamMembers) {
        this.teamMembers = teamMembers;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
