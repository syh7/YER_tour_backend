package YERgen2.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String judge;
    private String referee;
    private String location;
    @OneToMany
    private Set<Match> matches;

    public void setJudge(String judge) {
        this.judge = judge;
    }
    public void setReferee(String referee) {
        this.referee = referee;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    public String getJudge() {
        return judge;
    }
    public String getReferee() {
        return referee;
    }
    public String getLocation() {
        return location;
    }
    public Set<Match> getMatches() {
        return matches;
    }
}
