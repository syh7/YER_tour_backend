package YERgen2.demo.model;

import YERgen2.demo.DTO.EnrolmentDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long partnerLeagueNumber;
    @NotNull
    private int playerLevel;
    @NotNull
    private Discipline discipline;
    @NotNull
    @ManyToOne
    private Tournament tournament;
    @NotNull
    @ManyToMany
    private List<Participant> participants;

    public Enrolment() {
        participants = new ArrayList<>();
    }
    public Enrolment(@NotNull int playerLevel, @NotNull Discipline discipline, @NotNull Tournament tournament,
                     @NotNull List<Participant> participants) {
        this.playerLevel = playerLevel;
        this.discipline = discipline;
        this.tournament = tournament;
        this.participants = participants;
    }
    public Enrolment(long id, long partnerLeagueNumber, @NotNull int playerLevel, @NotNull Discipline discipline,
                     @NotNull Tournament tournament, @NotNull List<Participant> participants) {
        this.id = id;
        this.partnerLeagueNumber = partnerLeagueNumber;
        this.playerLevel = playerLevel;
        this.discipline = discipline;
        this.tournament = tournament;
        this.participants = participants;
    }
    public Enrolment(EnrolmentDTO enrolmentDTO, Tournament tournament, List<Participant> participants){
        this.id = enrolmentDTO.getId();
        this.discipline = enrolmentDTO.getDiscipline();
        this.playerLevel = enrolmentDTO.getPlayerLevel();
        this.partnerLeagueNumber = enrolmentDTO.getPartnerLeagueNumber();
        this.tournament = tournament;
        this.participants = participants;
    }

    public long getId() {
        return id;
    }
    public long getPartnerLeagueNumber() {
        return partnerLeagueNumber;
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
    public List<Participant> getParticipants() {
        return participants;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setPartnerLeagueNumber(long partnerLeagueNumber) {
        this.partnerLeagueNumber = partnerLeagueNumber;
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
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

}
