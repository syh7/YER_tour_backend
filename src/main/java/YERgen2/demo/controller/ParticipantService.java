package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import YERgen2.demo.model.Participant;
import YERgen2.demo.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParticipantService {
    
    @Autowired
    private ParticipantRepository participantRepository;

    ParticipantService(ParticipantRepository participantRepository){
        this.participantRepository = participantRepository;
    }

    public Participant save(Participant participant){
        return participantRepository.save(participant);
    }

    public Participant findById(Long id){
        return participantRepository.findById(id)
                .orElseThrow(() -> new ParticipantNotFoundException(id));
    }

    public Participant updateParticipant(long id, Participant newParticipant){
        return participantRepository.findById(id).map(participant -> {
            participant.setEmail(newParticipant.getEmail());
            participant.setPassword(newParticipant.getPassword());
            participant.setFirstName(newParticipant.getFirstName());
            participant.setLastName(newParticipant.getLastName());
            participant.setDateOfBirth(newParticipant.getDateOfBirth());
            participant.setPlayerLevel(newParticipant.getPlayerLevel());
            participant.setLeagueNumber(newParticipant.getLeagueNumber());
            participant.setEnrolment(newParticipant.getEnrolment());
            participant.setTeam(newParticipant.getTeam());
            return participantRepository.save(participant);
        }).orElseThrow( ()-> new ParticipantNotFoundException(id));
    }

    public Iterable <Participant> findAll(){
        return participantRepository.findAll();
    }

    public void deleteById(Long id) {
        participantRepository.deleteById(id);
    }

}
