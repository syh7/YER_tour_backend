package YERgen2.demo.model;

import javax.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int playerLevel;
    private Discipline discipline;
    @ManyToOne
    private Match match;
    @OneToOne
    private Tournament tournament;

    public long getId(){
        return id;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public Match getMatch() {
        return match;
    }
    public Tournament getTournament() {
        return tournament;
    }

    public void setId(long id){
        this.id = id;
    }
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

}
