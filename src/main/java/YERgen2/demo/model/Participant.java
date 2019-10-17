package YERgen2.demo.model;

import javax.persistence.Entity;


@Entity
public class Participant extends Account {

    private String firstName;
    private String lastName;
    private int playerLevel;
    private String dateOfBirth;

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getPlayerLevel(){
        return playerLevel;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setPlayerLevel(int playerLevel){
        this.playerLevel = playerLevel;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public void setId(long id){
        this.id = id;
    }

}
