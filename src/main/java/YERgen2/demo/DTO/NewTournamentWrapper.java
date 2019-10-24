package YERgen2.demo.DTO;

import YERgen2.demo.model.Tournament;

public class NewTournamentWrapper {

    private long adminId;
    private Tournament tournament;

    public NewTournamentWrapper(){}
    public NewTournamentWrapper(long adminId, Tournament tournament) {
        this.adminId = adminId;
        this.tournament = tournament;
    }

    public long getAdminId() {
        return adminId;
    }
    public Tournament getTournament() {
        return tournament;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

}
