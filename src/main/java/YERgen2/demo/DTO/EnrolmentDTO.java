package YERgen2.demo.DTO;

import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;

import java.util.List;

public class EnrolmentDTO  {

    private long id;
    private long partnerLeagueNumber;
    private int playerLevel;
    private Discipline discipline;
    private long tournamentId;
    private long[] participantIds;

    public EnrolmentDTO(){
        participantIds = new long[2];
    }
    public EnrolmentDTO(Enrolment enrolment){
        id = enrolment.getId();
        playerLevel = enrolment.getPlayerLevel();
        partnerLeagueNumber = enrolment.getPartnerLeagueNumber();
        discipline = enrolment.getDiscipline();
        tournamentId = enrolment.getTournament().getId();
        List<Participant> participants = enrolment.getParticipants();
        participantIds = new long[participants.size()];
        for(int i = 0; i < participants.size(); i++){
            participantIds[i] = participants.get(i).getId();
        }
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
    public long getTournamentId() {
        return tournamentId;
    }
    public long[] getParticipantIds() {
        return participantIds;
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
    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }
    public void setParticipantIds(long[] participantIds) {
        this.participantIds = participantIds;
    }

}
