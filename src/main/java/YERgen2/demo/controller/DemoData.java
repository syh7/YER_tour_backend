package YERgen2.demo.controller;

import YERgen2.demo.DTO.NewTournamentWrapper;
import YERgen2.demo.model.*;
import YERgen2.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component

public class DemoData {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private AccountService accountService;

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

        Tournament tournament1 = new Tournament("Stuban Toernooi 2019", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1));
        Tournament tournament2 = new Tournament("Helios Toernooi", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1));
        Tournament tournament3 = new Tournament("Stuban Toernooi 2020", LocalDate.of(2020, 10, 1),
                LocalDate.of(2020, 10, 1));
        NewTournamentWrapper newTournamentWrapper1 = new NewTournamentWrapper(admin1.getId(), tournament1);
        tournamentService.saveTournament(newTournamentWrapper1);
        NewTournamentWrapper newTournamentWrapper2 = new NewTournamentWrapper(admin2.getId(), tournament2);
        tournamentService.saveTournament(newTournamentWrapper2);
        NewTournamentWrapper newTournamentWrapper3 = new NewTournamentWrapper(admin1.getId(), tournament3);
        tournamentService.saveTournament(newTournamentWrapper3);

        Enrolment enrolment1 = new Enrolment(9, Discipline.MENSINGLES, tournament1);
        tournamentService.enrolParticipantInTournament(tournament1.getId(), part1, enrolment1);

    }

    private void testUniqueEmailConstraint(){
        LocalDate birth = LocalDate.of(2019,10,1);
        Admin admin3 = new Admin("Helios", "toernooi@helios.com", "wachtwoord123");
        Participant part3 = new Participant("toernooi@helios.com", "wachtwoord123", "Jozef", "Janssen", true, 6, birth);
        accountService.saveAdmin(admin3);
        accountService.saveParticipant(part3);
    }
}