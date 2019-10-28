package YERgen2.demo.model;

import YERgen2.demo.DTO.AdminDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class Admin extends Account {

    @NotNull
    private String name;
    @OneToMany(mappedBy = "admin")
    private List<Tournament> tournaments;

    public Admin(){
        this.tournaments = new ArrayList<>();
    }
    public Admin(@NotNull String name, @NotNull String email, @NotNull String password) {
        super(email, password);
        this.name = name;
        this.tournaments = new ArrayList<>();
    }
    public Admin(Admin copyAdmin){
        super(copyAdmin.getId(), copyAdmin.getEmail(), copyAdmin.getPassword());
        name = copyAdmin.getName();
        tournaments = copyAdmin.getTournaments();
    }
    public Admin(AdminDTO adminDTO, String password, List<Tournament> tournaments){
        super(adminDTO.getId(), adminDTO.getEmail(), password);
        this.name = adminDTO.getName();
        this.tournaments = tournaments;
    }

    public String getName() {
        return name;
    }
    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public boolean addTournament(Tournament tournament){
        return tournaments.add(tournament);
    }
    public boolean updateTournament(Tournament newTournament){
        for(Tournament tournament : tournaments){
            if(tournament.getId() == newTournament.getId()){
                tournaments.remove(tournament);
                return tournaments.add(newTournament);
            }
        }
        return false;
    }
    public boolean removeTournament(Tournament tournament){
        return tournaments.remove(tournament);
    }
    public void emptyTournaments(){
        tournaments.clear();
    }

}
