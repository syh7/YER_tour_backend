package YERgen2.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTests {

    private Account testAccount;

    @BeforeEach
    void setTestAccount(){
        testAccount = new Account(0, "email1@test.com", "notBlank");
    }

    @Test
    void setEmailChangesEmail(){
        String newEmail = "email2@test.com";
        testAccount.setEmail(newEmail);
        assertEquals(newEmail, testAccount.getEmail());
    }

    @Test
    void setPasswordChangesPassword(){
        String newPassword = "notBlank2";
        testAccount.setPassword(newPassword);
        assertEquals(newPassword, testAccount.getPassword());
    }

    @Test
    void setIdChangesId(){
        long newId = 1;
        testAccount.setId(1);
        assertEquals(newId, testAccount.getId());
    }

}
