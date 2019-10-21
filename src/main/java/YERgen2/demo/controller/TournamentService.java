package YERgen2.demo.controller;

import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    TournamentService(TournamentRepository tournamentRepository){
        this.tournamentRepository = tournamentRepository;
    }

    public Tournament save(Tournament participant){
        return tournamentRepository.save(participant);
    }

    public Optional<Tournament> findById(Long id){
        return tournamentRepository.findById(id);
    }

    public Iterable<Tournament> findAll(){
        return tournamentRepository.findAll();
    }

    public void deleteById(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Iterable<Tournament> findByName(String name){
        return tournamentRepository.findByName(name);
    }

    public boolean enrol(Long id, Enrolment enrolment){
        if(findById(id).isPresent()){
            Tournament tournament = findById(id).get();
            return tournament.enrol(enrolment);
        } else {
            return false;
        }
    }

    public Iterable<Enrolment> getEnrolment(long participantId, long tournamentId){
        if(findById(tournamentId).isPresent()){
            Tournament tournament = findById(tournamentId).get();
            List<Enrolment> list = new ArrayList<>();
            for(Enrolment enrol : tournament.getEnrolments()){
                if(enrol.getParticipant().getId() == participantId){
                    list.add(enrol);
                }
            }
            return list;
        } else {
            return null;
        }
    }

    public Iterable<Enrolment> getAllEnrolment(long id) {
        if(findById(id).isPresent()) {
            return findById(id).get().getEnrolments();
        } else {
            return null;
        }
    }
}
