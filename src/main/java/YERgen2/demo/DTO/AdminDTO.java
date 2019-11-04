package YERgen2.demo.DTO;

import YERgen2.demo.model.Admin;
import YERgen2.demo.model.Tournament;

import java.util.List;

public class AdminDTO {

    private long id;
    private String email;
    private String name;
    private long[] tournamentIds;

    public AdminDTO(){}
    public AdminDTO(Admin admin){
        this.id = admin.getId();
        this.email = admin.getEmail();
        this.name = admin.getName();
        List<Tournament> tournaments = admin.getTournaments();
        tournamentIds = new long[tournaments.size()];
        for(int i = 0; i < tournaments.size(); i++){
            tournamentIds[i] = tournaments.get(i).getId();
        }
    }

    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public long[] getTournamentIds() {
        return tournamentIds;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTournamentIds(long[] tournamentIds) {
        this.tournamentIds = tournamentIds;
    }
    
}
