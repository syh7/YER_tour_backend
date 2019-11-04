package YERgen2.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest {

    private Tournament testTournament;
    @Mock()
    private Admin mockAdmin;
    @Mock()
    private Enrolment mockEnrolment;
    @Mock()
    private Team mockTeam;
    @Mock()
    private Result mockResult;
    @Mock()
    private Game mockGame;

    @BeforeEach
    void setTestTournament(){
        testTournament = new Tournament();
    }

    @Test
    void setId() {
        long newId = 1;
        testTournament.setId(1);
        assertEquals(newId, testTournament.getId());
    }

    @Test
    void setName() {
        String newName = "new name";
        testTournament.setName(newName);
        assertEquals(newName, testTournament.getName());
    }

    @Test
    void setDescription() {
        String newDescription = "new description";
        testTournament.setDescription(newDescription);
        assertEquals(newDescription, testTournament.getDescription());
    }

    @Test
    void setReferee() {
        String newDescription = "new description";
        testTournament.setDescription(newDescription);
        assertEquals(newDescription, testTournament.getDescription());
    }

    @Test
    void setLocation() {
        String newDescription = "new description";
        testTournament.setDescription(newDescription);
        assertEquals(newDescription, testTournament.getDescription());
    }

    @Test
    void setStartDate() {
        LocalDate newStartDate = LocalDate.now();
        testTournament.setStartDate(newStartDate);
        assertEquals(newStartDate, testTournament.getStartDate());
    }

    @Test
    void setEndDate() {
        LocalDate newEndDate = LocalDate.now();
        testTournament.setEndDate(newEndDate);
        assertEquals(newEndDate, testTournament.getEndDate());
    }

    @Test
    void setEnrolDate() {
        LocalDate newEnrolDate = LocalDate.now();
        testTournament.setEnrolDate(newEnrolDate);
        assertEquals(newEnrolDate, testTournament.getEnrolDate());
    }

    @Test
    void setMaxDisciplines() {
        int newMaxDisciplines = 3;
        testTournament.setMaxDisciplines(newMaxDisciplines);
        assertEquals(newMaxDisciplines, testTournament.getMaxDisciplines());
    }

    @Test
    void setLevels() {
        int[] newLevels = new int[]{1,2,3};
        testTournament.setLevels(newLevels);
        assertEquals(newLevels, testTournament.getLevels());
    }

    @Test
    void setAdmin() {
        testTournament.setAdmin(mockAdmin);
        assertSame(testTournament.getAdmin(), mockAdmin);
    }

    @Test
    void addEnrolment() {
        testTournament.addEnrolment(mockEnrolment);
        assertTrue(testTournament.getEnrolments().contains(mockEnrolment));
    }

    @Test
    void removeEnrolment() {
        testTournament.addEnrolment(mockEnrolment);
        assertTrue(testTournament.getEnrolments().contains(mockEnrolment));
        testTournament.removeEnrolment(mockEnrolment);
        assertFalse(testTournament.getEnrolments().contains(mockEnrolment));
    }

    @Test
    void addTeam() {
        testTournament.addTeam(mockTeam);
        assertTrue(testTournament.getTeams().contains(mockTeam));
    }

    @Test
    void removeTeam() {
        testTournament.addTeam(mockTeam);
        assertTrue(testTournament.getTeams().contains(mockTeam));
        testTournament.removeTeam(mockTeam);
        assertFalse(testTournament.getTeams().contains(mockTeam));
    }

    @Test
    void addGame() {
        testTournament.addGame(mockGame);
        assertTrue(testTournament.getGames().contains(mockGame));
    }

    @Test
    void removeGame() {
        testTournament.addGame(mockGame);
        assertTrue(testTournament.getGames().contains(mockGame));
        testTournament.removeGame(mockGame);
        assertFalse(testTournament.getGames().contains(mockGame));
    }

    @Test
    void addResult() {
        testTournament.addResult(mockResult);
        assertTrue(testTournament.getResults().contains(mockResult));
    }

    @Test
    void removeResult() {
        testTournament.addResult(mockResult);
        assertTrue(testTournament.getResults().contains(mockResult));
        testTournament.removeResult(mockResult);
        assertFalse(testTournament.getResults().contains(mockResult));
    }
}