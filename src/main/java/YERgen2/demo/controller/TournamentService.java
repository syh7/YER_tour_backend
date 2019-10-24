package YERgen2.demo.controller;

import YERgen2.demo.DTO.NewEnrolmentWrapper;
import YERgen2.demo.DTO.EnrolmentDTO;
import YERgen2.demo.DTO.NewTournamentWrapper;
import YERgen2.demo.DTO.ParticipantDTO;
import YERgen2.demo.Exceptions.*;
import YERgen2.demo.model.Admin;
import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Tournament;
import YERgen2.demo.repositories.AdminRepository;
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
    @Autowired
    private AdminRepository adminRepository;

    TournamentService(EnrolmentRepository enrolmentRepository, TournamentRepository tournamentRepository,
                      ParticipantRepository participantRepository, AdminRepository adminRepository){
        this.enrolmentRepository = enrolmentRepository;
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
        this.adminRepository = adminRepository;
    }

    public Tournament saveTournament(NewTournamentWrapper newTournamentWrapper){
        Admin admin = adminRepository.findById(newTournamentWrapper.getAdminId())
                .orElseThrow(() -> new AdminNotFoundException(newTournamentWrapper.getAdminId()));
        if(admin.addTournament(newTournamentWrapper.getTournament())){
            return tournamentRepository.save(newTournamentWrapper.getTournament());
        } else {
            return null;
        }
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
            tournament.setLevels(newTournament.getLevels());
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
            enrolmentDTOs.add(new EnrolmentDTO(enrolment));
        });
        return enrolmentDTOs;
    }

    /*
        no check on discipline DEPRECATED
     */
    @Deprecated
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

    public ParticipantDTO enrolParticipantInTournament(long tournamentId, NewEnrolmentWrapper newEnrolmentWrapper) {
        Participant participant = participantRepository.findById(newEnrolmentWrapper.getParticipantId())
                .orElseThrow(() -> new ParticipantNotFoundException(newEnrolmentWrapper.getParticipantId()));
        newEnrolmentWrapper.getEnrolmentDTOs().forEach(enrolmentDTO -> {
            Enrolment enrolment = new Enrolment(enrolmentDTO, tournamentRepository.findById(enrolmentDTO.getTournamentId())
                    .orElseThrow(() -> new TournamentNotFoundException(enrolmentDTO.getTournamentId())));
            enrolment = enrolmentRepository.save(enrolment);
            participant.addEnrolment(enrolment);
            participantRepository.save(participant);
        });
        return new ParticipantDTO(participant);
    }

    @Deprecated
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
    public List<EnrolmentDTO> updateEnrolments(long id, NewEnrolmentWrapper newEnrolmentWrapper) {
        List<EnrolmentDTO> enrolmentDTOs = new ArrayList<>();
        Participant participant = participantRepository.findById(newEnrolmentWrapper.getParticipantId())
                .orElseThrow(() -> new ParticipantNotFoundException(newEnrolmentWrapper.getParticipantId()));
        newEnrolmentWrapper.getEnrolmentDTOs().forEach(enrolmentDTO -> {
            Enrolment enrolment = new Enrolment(enrolmentDTO, tournamentRepository.findById(enrolmentDTO.getTournamentId())
                    .orElseThrow(() -> new TournamentNotFoundException(enrolmentDTO.getTournamentId())));
            if(participant.updateEnrolment(enrolment)){
                enrolmentDTOs.add(enrolmentDTO);
            }
        });
        return enrolmentDTOs;
    }

}
