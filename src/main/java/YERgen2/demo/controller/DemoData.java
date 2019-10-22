package YERgen2.demo.controller;

import YERgen2.demo.model.Admin;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component

public class DemoData {


    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private EnrolmentRepository enrolmentRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameRepository gameRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        Admin admin1 = new Admin("Freek", "freek@test.com", "wachtwoord123");
        Admin admin2 = new Admin("Jan", "jan@test.com", "wachtwoord123");
        adminRepository.save(admin1);
        adminRepository.save(admin2);

        LocalDate birth = LocalDate.of(2019,10,1);
        Participant part1 = new Participant("gerard@test.com", "wachtwoord123", "Gerard",
                "Janssen", 6, birth);
        Participant part2 = new Participant("jozef@test.com", "wachtwoord123", "Jozef",
                "Janssen", 6, birth);
        participantRepository.save(part1);
        participantRepository.save(part2);

        Tournament tournament1 = new Tournament("BesteToernooi", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1), admin1);
        Tournament tournament2 = new Tournament("BesteToernooi", LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 10, 1), admin2);
        tournamentRepository.save(tournament1);
        tournamentRepository.save(tournament2);
    }
}