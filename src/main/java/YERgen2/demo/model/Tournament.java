package YERgen2.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String judge;
    private String referee;
    private String location;

    public void setJudge(String judge) {
        this.judge = judge;
    }
    public void setReferee(String referee) {
        this.referee = referee;
    }
    public void setLocation(String location) {
        this.location = location;
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

}
