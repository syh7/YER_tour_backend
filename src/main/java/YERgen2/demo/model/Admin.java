package YERgen2.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("1")
public class Admin extends Account {

    @NotNull
    private String name;

    public Admin(){}
    public Admin(@NotNull String name, @NotNull String email, @NotNull String password) {
        super(email, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
