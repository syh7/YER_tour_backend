package YERgen2.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToMany
    List<ParticipantAccount> teamMembers;

}
