package YERgen2.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Participant participant;
    private long partnerLeagueNumber;
    private int playerLevel;
    private Discipline discipline;

    public long getId() {
        return id;
    }
    public Participant getParticipant() {
        return participant;
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

    public void setId(long id) {
        this.id = id;
    }
    public void setParticipant(Participant participant) {
        this.participant = participant;
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

}
