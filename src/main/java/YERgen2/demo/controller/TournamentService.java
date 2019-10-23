package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.AlreadyEnrolledException;
import YERgen2.demo.Exceptions.EnrolmentNotFoundException;
import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Tournament;
import YERgen2.demo.repositories.EnrolmentRepository;
import YERgen2.demo.repositories.ParticipantRepository;
import YERgen2.demo.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private EnrolmentRepository enrolmentRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    TournamentService(EnrolmentRepository enrolmentRepository, TournamentRepository tournamentRepository, ParticipantRepository participantRepository){
        this.enrolmentRepository = enrolmentRepository;
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
    }

    public Tournament saveTournament(Tournament tournament){
        return tournamentRepository.save(tournament);
    }
    public Enrolment saveEnrolment(Enrolment enrolment){
        return enrolmentRepository.save(enrolment);
    }

    public Tournament findTournamentById(long id){
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id));
    }
    public Enrolment findEnrolmentById(long id){
        return enrolmentRepository.findById(id)
                .orElseThrow(() -> new EnrolmentNotFoundException(id));
    }

    public Tournament updateTournament(long id, Tournament newTournament){
        return tournamentRepository.findById(id).map(tournament -> {
            tournament.setName(newTournament.getName());
            tournament.setDescription(newTournament.getDescription());
            tournament.setReferee(newTournament.getReferee());
            tournament.setLocation(newTournament.getLocation());
            tournament.setStartDate(newTournament.getStartDate());
            tournament.setEndDate(newTournament.getEndDate());
            tournament.setEnrolDate(newTournament.getEnrolDate());
            tournament.setMaxDisciplines(newTournament.getMaxDisciplines());
            tournament.setCategories(newTournament.getCategories());
            tournament.setAdmin(newTournament.getAdmin());
            return tournamentRepository.save(tournament);
        }).orElseThrow(() -> new TournamentNotFoundException(id));
    }

    public boolean existsTournamentById(long id){
        return tournamentRepository.existsById(id);
    }

    public Iterable<Tournament> findAllTournament(){
        return tournamentRepository.findAll();
    }
    public Iterable<Enrolment> findAllEnrolment(){
        return enrolmentRepository.findAll();
    }

    public void deleteTournamentById(long id) {
        tournamentRepository.deleteById(id);
    }
    public void deleteEnrolmentById(long id) {
        enrolmentRepository.deleteById(id);
    }

    public Iterable<Tournament> findTournamentByNameContaining(String name){
        return tournamentRepository.findByNameContaining(name);
    }

    public Iterable<Enrolment> findEnrolmentByTournamentId(long tournamentId){
        return enrolmentRepository.findByTournamentId(tournamentId);
    }

    public Enrolment enrolParticipantInTournament(long tournamentId, Enrolment enrolment, Participant participant){
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
