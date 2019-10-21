package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
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

    @Override
    public String toString(){
        String str = "ID: " + id + "\n";
        str += "Name: " + firstName + " " + lastName + "\n";
        str += "Playerlevel: " + playerLevel + "\n";
        str += "Email: " + getEmail();
        str += "Password: " + getPassword();
        return str;
    }

}
