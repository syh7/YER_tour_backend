package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.model.Enrolment;
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

    EnrolmentService(EnrolmentRepository enrolmentRepository){
        this.enrolmentRepository = enrolmentRepository;
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

    public Enrolment save(long tournamentID, Enrolment enrolment){
        if(!tournamentRepository.existsById(tournamentID)){
            throw new TournamentNotFoundException(tournamentID);
        }
        return enrolmentRepository.save(enrolment);
    }

}
