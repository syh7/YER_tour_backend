package YERgen2.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class AdminTests {

    private Admin testAdmin;

    @Mock()
    private Tournament mockTournament;

    @BeforeEach
    void setTestAdmin(){
        testAdmin = new Admin(0, "name", "admin@test.com", "password");
    }

    @Test
    void setEmailChangesEmail(){
        String newEmail = "email2@test.com";
        testAdmin.setEmail(newEmail);
        assertEquals(newEmail, testAdmin.getEmail());
    }

    @Test
    void setPasswordChangesPassword(){
        String newPassword = "notBlank2";
        testAdmin.setPassword(newPassword);
        assertEquals(newPassword, testAdmin.getPassword());
    }

    @Test
    void setIdChangesId(){
        long newId = 1;
        testAdmin.setId(1);
        assertEquals(newId, testAdmin.getId());
    }

    @Test
    void setNameChangesName(){
        String newName = "new name";
        testAdmin.setName(newName);
        assertEquals(newName, testAdmin.getName());
    }

    @Test
    void addTournamentAddsTournament(){
        testAdmin.addTournament(mockTournament);
        assertTrue(testAdmin.getTournaments().contains(mockTournament));
    }

    @Test
    void removeTournamentRemovesTournament(){
        testAdmin.addTournament(mockTournament);
        assertTrue(testAdmin.getTournaments().contains(mockTournament));
        testAdmin.removeTournament(mockTournament);
        assertFalse(testAdmin.getTournaments().contains(mockTournament));
    }

}
