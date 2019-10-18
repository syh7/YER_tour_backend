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
    private int playerLevel;
    private Discipline discipline;

    public List<Participant> getTeamMembers() {
        return teamMembers;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setTeamMembers(List<Participant> teamMembers) {
        this.teamMembers = teamMembers;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
