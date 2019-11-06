package YERgen2.demo.controller;

import YERgen2.demo.DTO.BetDTO;
import YERgen2.demo.DTO.GameDTO;
import YERgen2.demo.DTO.NewTournamentWrapper;
import YERgen2.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component

public class DemoData {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BetService betService;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        Admin admin1 = new Admin("Stuban", "toernooi@stuban.com", "wachtwoord123");
        Admin admin2 = new Admin("Helios", "toernooi@helios.com", "wachtwoord123");
        accountService.saveAdmin(admin1);
        accountService.saveAdmin(admin2);

        LocalDate birth = LocalDate.of(2019,10,1);
        Participant part1 = new Participant("gerard@test.com", "wachtwoord123", "Gerard",
                "Janssen", true, 6, birth);
        Participant part2 = new Participant("jozef@test.com", "wachtwoord123", "Jozef",
                "Janssen", true, 6, birth);
        accountService.saveParticipant(part1);
        accountService.saveParticipant(part2);

        //=Betting=
        Bettor bettor1 = new Bettor("Sjaak", "sjaak@hotmail.com", "wachtwoord123", 100);
        accountService.saveBettor(bettor1);
        Bettor bettor2 = new Bettor("Gart", "gart@aol.com", "wachtwoord123", 20);
        accountService.saveBettor(bettor2);

        Tournament tournament1 = new Tournament("Stuban Toernooi 2019", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1), admin1);
        tournament1.setMaxDisciplines(3);
        Tournament tournament2 = new Tournament("Helios Toernooi", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1), admin2);
        Tournament tournament3 = new Tournament("Stuban Toernooi 2020", LocalDate.of(2020, 10, 1),
                LocalDate.of(2020, 10, 1), admin1);
        NewTournamentWrapper newTournamentWrapper1 = new NewTournamentWrapper(admin1.getId(), tournament1);
        tournamentService.saveTournament(newTournamentWrapper1);
        NewTournamentWrapper newTournamentWrapper2 = new NewTournamentWrapper(admin2.getId(), tournament2);
        tournamentService.saveTournament(newTournamentWrapper2);
        NewTournamentWrapper newTournamentWrapper3 = new NewTournamentWrapper(admin1.getId(), tournament3);
        tournamentService.saveTournament(newTournamentWrapper3);

        List<Participant> participantList1 = new ArrayList<>();
        participantList1.add(part1);
        List<Participant> participantList2 = new ArrayList<>();
        participantList2.add(part2);
        Enrolment enrolment1 = new Enrolment(9, Discipline.MENSINGLES, tournament1, participantList1);
        tournamentService.enrolParticipantInTournament(tournament1.getId(), part1, enrolment1);
        Enrolment enrolment2 = new Enrolment(9, Discipline.MENSINGLES, tournament1, participantList2);
        tournamentService.enrolParticipantInTournament(tournament1.getId(), part2, enrolment2);

        List<Team> singleTeams = tournamentService.makeSingleTeams(tournament1.getId());

        //Make game1
        Game game1 = new Game(Stage.FINAL, Discipline.MENSINGLES, tournament1, singleTeams.get(0), singleTeams.get(1),
                singleTeams.get(0).getPlayerLevel());
        GameDTO gameDTO1 = tournamentService.saveGame(tournament1.getId(), new GameDTO(game1));
        game1 = new Game(gameDTO1, tournament1, singleTeams.get(0), singleTeams.get(1), new ArrayList<>());

        int[][] score = new int[][]{{10,21}, {21,10}, {10,21}};
        game1 = tournamentService.finishGame(tournament1.getId(), game1.getId(), score);

        //make game2
        Game game2 = new Game(Stage.FINAL, Discipline.MENSINGLES, tournament1, singleTeams.get(0), singleTeams.get(1),
                singleTeams.get(0).getPlayerLevel());
        GameDTO gameDTO2 = tournamentService.saveGame(tournament1.getId(), new GameDTO(game2));
        game2 = new Game(gameDTO2, tournament1, singleTeams.get(0), singleTeams.get(1), new ArrayList<>());

        score = new int[][]{{21,10}, {21,10}};
        game2 = tournamentService.finishGame(tournament1.getId(), game2.getId(), score);

        //make game3
        Game game3 = new Game(Stage.FINAL, Discipline.MENSINGLES, tournament1, singleTeams.get(0), singleTeams.get(1),
                singleTeams.get(0).getPlayerLevel());
        GameDTO gameDTO3 = tournamentService.saveGame(tournament1.getId(), new GameDTO(game3));
        game3 = new Game(gameDTO3, tournament1, singleTeams.get(0), singleTeams.get(1), new ArrayList<>());

        score = new int[][]{{10,21}, {21,10}, {10,21}};
        game3 = tournamentService.finishGame(tournament1.getId(), game3.getId(), score);

        Team winningteam = game3.getWinningTeam();
        System.out.println(winningteam.getParticipants().get(0).getFirstName());

        //=Betting=
        Bet bet1 = new Bet(20, bettor1, game3, singleTeams.get(0));
        betService.addBetToBettor(bettor1.getId(), new BetDTO(bet1));


        tournament1 = tournamentService.finishDiscipline(tournament1.getId(), Discipline.MENSINGLES);
        List<Result> tournamentResults = tournament1.getResults();
        Team winners = tournamentResults.get(0).getWinners();
        System.out.println(winners.getParticipants().get(0).getFirstName());
    }

    private void testUniqueEmailConstraint(){
        LocalDate birth = LocalDate.of(2019,10,1);
        Admin admin3 = new Admin("Helios", "toernooi@helios.com", "wachtwoord123");
        Participant part3 = new Participant("toernooi@helios.com", "wachtwoord123", "Jozef", "Janssen", true, 6, birth);
        accountService.saveAdmin(admin3);
        accountService.saveParticipant(part3);
    }
}