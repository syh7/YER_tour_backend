package YERgen2.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

public class ParticipantTests {

    private Participant testParticipant;
    @Mock()
    private Enrolment mockEnrolment;
    @Mock()
    private Team mockTeam;

    @BeforeEach
    void setTestParticipant(){
        LocalDate birth = LocalDate.of(1970, 1, 1);
        testParticipant = new Participant("email@test.com", "password", "firstName",
                "lastName", true, 9, birth);
    }

    @Test
    void setEmailChangesEmail(){
        String newEmail = "email2@test.com";
        testParticipant.setEmail(newEmail);
        assertEquals(newEmail, testParticipant.getEmail());
    }

    @Test
    void setPasswordChangesPassword(){
        String newPassword = "notBlank2";
        testParticipant.setPassword(newPassword);
        assertEquals(newPassword, testParticipant.getPassword());
    }

    @Test
    void setIdChangesId(){
        long newId = 1;
        testParticipant.setId(1);
        assertEquals(newId, testParticipant.getId());
    }

    @Test
    void setFirstNameChangesFirstName(){
        String newFirstName = "firstName2";
        testParticipant.setFirstName(newFirstName);
        assertEquals(newFirstName, testParticipant.getFirstName());
    }

    @Test
    void setLastNameChangesLastName(){
        String newLastName = "lastName2";
        testParticipant.setLastName(newLastName);
        assertEquals(newLastName, testParticipant.getLastName());
    }

    @Test
    void setPlayerLevelChangesPlayerLevel(){
        int newLevel = 1;
        testParticipant.setPlayerLevel(newLevel);
        assertEquals(newLevel, testParticipant.getPlayerLevel());
    }

    @Test
    void setMaleChangesMale(){
        boolean newIsMale = false;
        testParticipant.setMale(newIsMale);
        assertEquals(newIsMale, testParticipant.isMale());
    }

    @Test
    void setLeagueNumberChangesLeagueNumber(){
        int newLeagueNumber = 1;
        testParticipant.setLeagueNumber(newLeagueNumber);
        assertEquals(newLeagueNumber, testParticipant.getLeagueNumber());
    }

    @Test
    void setDateOfBirthChanges(){
        LocalDate newBirth = LocalDate.now();
        testParticipant.setDateOfBirth(newBirth);
        assertEquals(newBirth, testParticipant.getDateOfBirth());
    }

    @Test
    void addEnrolmentAddsEnrolment(){
        testParticipant.addEnrolment(mockEnrolment);
        assertTrue(testParticipant.getEnrolments().contains(mockEnrolment));
    }

    @Test
    void removeEnrolmentRemovesEnrolment(){
        testParticipant.addEnrolment(mockEnrolment);
        assertTrue(testParticipant.getEnrolments().contains(mockEnrolment));
        testParticipant.removeEnrolment(mockEnrolment);
        assertFalse(testParticipant.getEnrolments().contains(mockEnrolment));
    }

    @Test
    void addTeamAddsTeam(){
        testParticipant.addTeam(mockTeam);
        assertTrue(testParticipant.getTeams().contains(mockTeam));
    }

    @Test
    void removeTeamRemovesTeam(){
        testParticipant.addTeam(mockTeam);
        assertTrue(testParticipant.getTeams().contains(mockTeam));
        testParticipant.removeTeam(mockTeam);
        assertFalse(testParticipant.getTeams().contains(mockTeam));
    }

}
