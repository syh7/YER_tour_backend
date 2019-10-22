package YERgen2.demo.controller;

import YERgen2.demo.model.Admin;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component

public class DemoData {

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private AdminRepository adminRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        Tournament tournament = new Tournament("BesteToernooi", "Sjaak", "Nijmegen",
                LocalDate.of(2019, 10, 1), LocalDate.of(2019, 10, 1),
                LocalDate.of(2019, 9, 6), 2, new int[] {2,5,8}, adminRepository.save(new Admin()));
        tournamentRepository.save(tournament);
    }
}