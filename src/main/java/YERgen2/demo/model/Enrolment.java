package YERgen2.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int playerLevel;
    @NotNull
    private Discipline discipline;
    @NotNull
    @ManyToOne
    private Tournament tournament;

    public long getId() {
        return id;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public Tournament getTournament() {
        return tournament;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

}
