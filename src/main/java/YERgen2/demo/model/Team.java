package YERgen2.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int playerLevel;
    @NotNull
    private Discipline discipline;
    @ManyToOne
    private Game game;
    @NotNull
    @ManyToOne
    private Tournament tournament;

    public Team() {}
    public Team(@NotNull int playerLevel, @NotNull Discipline discipline, @NotNull Tournament tournament) {
        this.playerLevel = playerLevel;
        this.discipline = discipline;
        this.tournament = tournament;
    }
    public Team(Enrolment enrolment){
        playerLevel = enrolment.getPlayerLevel();
        discipline = enrolment.getDiscipline();
        tournament = enrolment.getTournament();
    }

    public long getId(){
        return id;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public Game getGame() {
        return game;
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
    public void setGame(Game game) {
        this.game = game;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

}
