package YERgen2.demo.model;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype",
        discriminatorType = DiscriminatorType.INTEGER)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public long getId(){
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(long id){
        this.id = id;
    }

}
