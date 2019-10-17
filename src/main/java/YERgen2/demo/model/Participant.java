package YERgen2.demo.model;

import javax.persistence.Entity;

@Entity
public class Participant extends Account {

    String firstName;
    String lastName;
    int playerLevel;
    String dateOfBirth;

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

}
