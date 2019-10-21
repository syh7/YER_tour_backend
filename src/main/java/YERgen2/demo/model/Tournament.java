package YERgen2.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String referee;
    private String location;
    private LocalDate date;
    @OneToMany
    private Set<Match> matches;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setReferee(String referee) {
        this.referee = referee;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReferee() {
        return referee;
    }
    public String getLocation() {
        return location;
    }
    public LocalDate getDate() {
        return date;
    }
    public Set<Match> getMatches() {
        return matches;
    }


}
