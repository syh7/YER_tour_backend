package YERgen2.demo.controller;

import YERgen2.demo.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public Participant save(Participant participant){
        return participantRepository.save(participant);
    }

    public Optional<Participant> findById(Long id){
        return participantRepository.findById(id);
    }

    public Iterable <Participant> findAll(){
        return participantRepository.findAll();
    }

    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }
    
}
