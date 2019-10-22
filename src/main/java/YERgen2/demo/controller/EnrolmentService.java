package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.AlreadyEnrolledException;
import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;
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

    public Enrolment save(long tournamentID, Enrolment enrolment, Participant participant){
        if(!tournamentRepository.existsById(tournamentID)){
            throw new TournamentNotFoundException(tournamentID);
        } else if(!participantRepository.existsById(participant.getId())){
            throw new ParticipantNotFoundException(participant.getId());
        }
        return enrolmentRepository.save(enrolment);
        /*
        if(participantRepository.existsEnrolmentById(participant.getId(), enrolment.getId())) {
            return enrolmentRepository.save(enrolment);
        } else {
            throw new AlreadyEnrolledException(participant.getId(), enrolment.getId());
        }
        */
    }

}
