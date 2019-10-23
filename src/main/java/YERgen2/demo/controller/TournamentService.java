package YERgen2.demo.controller;

import YERgen2.demo.DTO.EnrolRequestWrapper;
import YERgen2.demo.DTO.EnrolmentDTO;
import YERgen2.demo.Exceptions.NotModifiedException;
import YERgen2.demo.Exceptions.EnrolmentNotFoundException;
import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import YERgen2.demo.Exceptions.TournamentNotFoundException;
import YERgen2.demo.model.Discipline;
import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Tournament;
import YERgen2.demo.repositories.EnrolmentRepository;
import YERgen2.demo.repositories.ParticipantRepository;
import YERgen2.demo.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    public Iterable<EnrolmentDTO> findEnrolmentByTournamentId(long tournamentId){
        List<EnrolmentDTO> enrolmentDTOs = new ArrayList<>();
        enrolmentRepository.findByTournamentId(tournamentId).forEach(enrolment -> {
            EnrolmentDTO enrolmentDTO = new EnrolmentDTO();
            enrolmentDTO.setId(enrolment.getId());
            enrolmentDTO.setPlayerLevel(enrolment.getPlayerLevel());
            enrolmentDTO.setPartnerLeagueNumber(enrolment.getPartnerLeagueNumber());
            enrolmentDTO.setDiscipline(enrolment.getDiscipline());
            enrolmentDTO.setTournamentId(enrolment.getTournament().getId());
            enrolmentDTOs.add(enrolmentDTO);
        });
        return enrolmentDTOs;
    }

    /*
        no check on discipline
     */
    public boolean enrolParticipantInTournament(long tournamentId, Participant participant, Enrolment enrolment){
        if(!tournamentRepository.findById(tournamentId).isPresent()){
            throw new TournamentNotFoundException(tournamentId);
        } else if(!participantRepository.existsById(participant.getId())){
            throw new ParticipantNotFoundException(participant.getId());
        } else if(tournamentId != enrolment.getTournament().getId()){
            throw new NotModifiedException("TournamentIDs don't match: " + tournamentId + " - " + enrolment.getTournament().getId());
        }
        if(participant.getNumberEnrolmentsInTournament(tournamentId) < tournamentRepository.findById(tournamentId).get().getMaxDisciplines()) {
            if(participant.addEnrolment(enrolment)) {
                enrolmentRepository.save(enrolment);
                participantRepository.save(participant);
                return true;
            } else {
                throw new NotModifiedException("Participant " + participant.getId() + " already enrolled in unchanged enrolment " + enrolment.getId());
            }
        } else {
            throw new NotModifiedException("Participant " + participant.getId() + " reached max enrolments in tournament " + tournamentId);
        }
    }

    public Participant enrolParticipantInTournament(long tournamentId, EnrolRequestWrapper enrolRequestWrapper) {
        Participant participant = participantRepository.findById(enrolRequestWrapper.getParticipantId())
                .orElseThrow(() -> new ParticipantNotFoundException(enrolRequestWrapper.getParticipantId()));
        enrolRequestWrapper.getEnrolmentDTOs().forEach( enrolmentDTO -> {
            Enrolment enrolment = new Enrolment();
            enrolment.setId(enrolmentDTO.getId());
            enrolment.setPlayerLevel(enrolmentDTO.getPlayerLevel());
            enrolment.setPartnerLeagueNumber(enrolmentDTO.getPartnerLeagueNumber());
            enrolment.setDiscipline(enrolmentDTO.getDiscipline());
            enrolment.setTournament(tournamentRepository.findById(enrolmentDTO.getTournamentId())
                .orElseThrow(() -> new TournamentNotFoundException(enrolmentDTO.getTournamentId())));
            enrolment = enrolmentRepository.save(enrolment);
            participant.addEnrolment(enrolment);
            participantRepository.save(participant);
        });
        return participant;
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

    public List<EnrolmentDTO> updateEnrolments(long id, EnrolRequestWrapper enrolRequestWrapper) {
        List<EnrolmentDTO> enrolmentDTOs = new ArrayList<>();
        Participant participant = participantRepository.findById(enrolRequestWrapper.getParticipantId())
                .orElseThrow(() -> new ParticipantNotFoundException(enrolRequestWrapper.getParticipantId()));

        enrolRequestWrapper.getEnrolmentDTOs().forEach(enrolmentDTO -> {
            Enrolment enrolment = new Enrolment();
            enrolment.setId(enrolmentDTO.getId());
            enrolment.setPlayerLevel(enrolmentDTO.getPlayerLevel());
            enrolment.setPartnerLeagueNumber(enrolmentDTO.getPartnerLeagueNumber());
            enrolment.setDiscipline(enrolmentDTO.getDiscipline());
            enrolment.setTournament(tournamentRepository.findById(enrolmentDTO.getTournamentId())
                    .orElseThrow(() -> new TournamentNotFoundException(enrolmentDTO.getTournamentId())));
            if(participant.updateEnrolment(enrolment)){
                enrolmentDTOs.add(enrolmentDTO);
            }
        });

        return enrolmentDTOs;
    }
}
