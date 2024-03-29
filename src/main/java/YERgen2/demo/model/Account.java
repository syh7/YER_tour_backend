package YERgen2.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype",
        discriminatorType = DiscriminatorType.INTEGER)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(unique=true)
    private String email;
    @NotBlank
    private String password;

    public Account() {}
    public Account(@NotBlank String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }
    public Account(long id, @NotBlank String email, @NotBlank String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

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
