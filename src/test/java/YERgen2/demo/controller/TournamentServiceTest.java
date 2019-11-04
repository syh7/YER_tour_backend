package YERgen2.demo.controller;

import YERgen2.demo.model.Tournament;
import YERgen2.demo.repositories.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class TournamentServiceTest {

    @Autowired
    private TournamentService tournamentService;

    @MockBean
    private TournamentRepository tournamentRepository;
    @MockBean
    private EnrolmentRepository enrolmentRepository;
    @MockBean
    private ParticipantRepository participantRepository;
    @MockBean
    private AdminRepository adminRepository;
    @MockBean
    private TeamRepository teamRepository;
    @MockBean
    private GameRepository gameRepository;
    @MockBean
    private ResultRepository resultRepository;

    @Mock
    private Tournament mockTournament;

    @Before
    public void setUp(){

    }

    @Before
    public void setTournamentRepository(){

    }

    @Test
    void saveTournament() {
    }

    @Test
    void saveEnrolment() {
    }

    @Test
    void saveGames() {
    }

    @Test
    void saveGame() {
    }

    @Test
    void findTournamentById() {
    }

    @Test
    void findEnrolmentById() {
    }

    @Test
    void updateTournament() {
    }

    @Test
    void updateGame() {
    }

    @Test
    void existsTournamentById() {
    }

    @Test
    void findAllTournament() {
    }

    @Test
    void findAllEnrolment() {
    }

    @Test
    void deleteTournamentById() {
    }

    @Test
    void deleteEnrolmentById() {
    }

    @Test
    void findTournamentByNameContaining() {
    }

    @Test
    void findEnrolmentByTournamentId() {
    }

    @Test
    void findGamesByTournamentId() {
    }

    @Test
    void findTeamsByTournamentId() {
    }

    @Test
    void enrolParticipantInTournament() {
    }

    @Test
    void testEnrolParticipantInTournament() {
    }

    @Test
    void updateEnrolment() {
    }

    @Test
    void updateEnrolments() {
    }

    @Test
    void findEnrolmentByTournamentIdAndDiscipline() {
    }

    @Test
    void makeSingleTeams() {
    }

    @Test
    void finishGame() {
    }

    @Test
    void finishDiscipline() {
    }

    @Test
    void getTournamentResults() {
    }

    @Test
    void getTournamentResultByDisciplineAndPlayerLevel() {
    }
}