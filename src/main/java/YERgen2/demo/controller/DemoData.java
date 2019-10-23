package YERgen2.demo.controller;

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
                "Janssen", 6, birth);
        Participant part2 = new Participant("jozef@test.com", "wachtwoord123", "Jozef",
                "Janssen", 6, birth);
        accountService.saveParticipant(part1);
        accountService.saveParticipant(part2);

        Tournament tournament1 = new Tournament("Stuban Toernooi", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1));
        Tournament tournament2 = new Tournament("Helios Toernooi", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1));
        tournamentService.saveTournament(tournament1);
        accountService.addTournamentToAdmin(admin1.getId(), tournament1);
        tournamentService.saveTournament(tournament2);
        accountService.addTournamentToAdmin(admin2.getId(), tournament2);

        Enrolment enrolment1 = new Enrolment(9, Discipline.MENSINGLES, tournament1);
        tournamentService.enrolParticipantInTournament(tournament1.getId(), part1, enrolment1);
    }
}