package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.AlreadyEnrolledException;
import YERgen2.demo.Exceptions.EnrolmentNotFoundException;
import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;
import YERgen2.demo.repositories.EnrolmentRepository;
import YERgen2.demo.repositories.ParticipantRepository;
import YERgen2.demo.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EnrolmentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private EnrolmentRepository enrolmentRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    EnrolmentService(EnrolmentRepository enrolmentRepository, TournamentRepository tournamentRepository){
        this.enrolmentRepository = enrolmentRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public Enrolment save(Enrolment enrolment){
        return enrolmentRepository.save(enrolment);
    }

    public Optional<Enrolment> findById(long id){
        return enrolmentRepository.findById(id);
    }

    public Iterable<Enrolment> findAll(){
        return enrolmentRepository.findAll();
    }

    public void deleteById(long id) {
        enrolmentRepository.deleteById(id);
    }

    public Iterable<Enrolment> findByTournamentId(long tournamentId){
        return enrolmentRepository.findByTournamentId(tournamentId);
    }

    public Enrolment save(long tournamentId, Enrolment enrolment, Participant participant){
        if(!tournamentRepository.existsById(tournamentId)){
            throw new TournamentNotFoundException(tournamentId);
        } else if(!participantRepository.existsById(participant.getId())){
            throw new ParticipantNotFoundException(participant.getId());
        }
        if(participant.addEnrolment(enrolment)) {
            return enrolmentRepository.save(enrolment);
        } else {
            throw new AlreadyEnrolledException(participant.getId(), enrolment.getId());
        }
    }

    public Enrolment updateEnrolment(long tournamentId, Enrolment newEnrolment){
        if(!tournamentRepository.existsById(tournamentId)){
            throw new TournamentNotFoundException(tournamentId);
        } else {
            return enrolmentRepository.findById(newEnrolment.getId())
                    .map(enrolment -> {
                        enrolment.setDiscipline(newEnrolment.getDiscipline());
                        enrolment.setPartnerLeagueNumber(newEnrolment.getPartnerLeagueNumber());
                        enrolment.setPlayerLevel(newEnrolment.getPlayerLevel());
                        enrolment.setTournament(newEnrolment.getTournament());
                        return enrolmentRepository.save(enrolment);
                    }).orElseThrow(() -> new EnrolmentNotFoundException(newEnrolment.getId()));
        }
    }

}
