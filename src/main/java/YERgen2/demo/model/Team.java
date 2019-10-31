package YERgen2.demo.model;

import YERgen2.demo.DTO.TeamDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int playerLevel;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Discipline discipline;
    @ManyToMany
    private List<Game> games;
    @NotNull
    @ManyToOne
    private Tournament tournament;
    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    private List<Participant> participants;
    @OneToMany(mappedBy = "team")
    private List<Bet> bets;

    public Team() {
        participants = new ArrayList<>();
        games = new ArrayList<>();
    }
    public Team(@NotNull int playerLevel, @NotNull Discipline discipline, @NotNull Tournament tournament,
                @NotNull List<Participant> participants) {
        this.playerLevel = playerLevel;
        this.discipline = discipline;
        this.tournament = tournament;
        this.participants = participants.stream().map(
                Participant::new).collect(Collectors.toList());
    }
    public Team(Enrolment enrolment){
        playerLevel = enrolment.getPlayerLevel();
        discipline = enrolment.getDiscipline();
        tournament = enrolment.getTournament();
        participants = enrolment.getParticipants().stream().map(Participant::new).collect(Collectors.toList());
    }
    public Team(TeamDTO teamDTO, List<Game> games, Tournament tournament){
        id = teamDTO.getId();
        playerLevel = teamDTO.getPlayerLevel();
        discipline = teamDTO.getDiscipline();
        this.games = games;
        this.tournament = tournament;
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
    public List<Game> getGames() {
        return games;
    }
    public Tournament getTournament() {
        return tournament;
    }
    public List<Participant> getParticipants() {
        return participants;
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
    public void setGames(List<Game> games) {
        this.games = games;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public boolean addGame(Game game) {
        return games.add(game);
    }
    public boolean removeGame(Game game){
        return games.remove(game);
    }

}
