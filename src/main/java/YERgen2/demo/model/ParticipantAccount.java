package YERgen2.demo.model;

import javax.persistence.Entity;

@Entity
public class ParticipantAccount extends Account {

    String firstName;
    String lastName;
    int playerLevel;
    String dateOfBirth;

}
