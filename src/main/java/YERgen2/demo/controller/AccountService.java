package YERgen2.demo.controller;

import YERgen2.demo.DTO.AdminDTO;
import YERgen2.demo.DTO.ParticipantDTO;
import YERgen2.demo.Exceptions.*;
import YERgen2.demo.model.Admin;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Tournament;
import YERgen2.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private EnrolmentRepository enrolmentRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TournamentRepository tournamentRepository;

    AccountService(AdminRepository adminRepository, ParticipantRepository participantRepository,
                   EnrolmentRepository enrolmentRepository, TeamRepository teamRepository,
                   TournamentRepository tournamentRepository){
        this.adminRepository = adminRepository;
        this.participantRepository = participantRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public Admin saveAdmin(Admin participant){
        return adminRepository.save(participant);
    }
    public Participant saveParticipant(Participant participant){
        return participantRepository.save(participant);
    }

    public AdminDTO findAdminById(long id){
        Admin admin=  adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException(id));
        return new AdminDTO(admin);
    }
    public ParticipantDTO findParticipantById(Long id){
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new ParticipantNotFoundException(id));
        return new ParticipantDTO(participant);
    }

    public Iterable <AdminDTO> findAllAdmin(){
        List<AdminDTO> adminDTOs = new ArrayList<>();
        adminRepository.findAll().forEach(admin -> {
            adminDTOs.add(new AdminDTO(admin));
        });
        return adminDTOs;
    }
    public Iterable <ParticipantDTO> findAllParticipant(){
        List<ParticipantDTO> participantDTOs = new ArrayList<>();
        participantRepository.findAll().forEach(participant -> {
            participantDTOs.add(new ParticipantDTO(participant));
        });
        return participantDTOs;
    }

    public void deleteAdminById(long id) {
        adminRepository.deleteById(id);
    }
    public void deleteParticipantById(Long id) {
        participantRepository.deleteById(id);
    }

    public AdminDTO updateAdmin(long id, Admin newAdmin) {
        return adminRepository.findById(id).map(admin -> {
            return new AdminDTO(adminRepository.save(new Admin(admin)));
        }).orElseThrow(() -> new AdminNotFoundException(newAdmin.getId()));
    }
    public AdminDTO updateAdminDTO(long id, AdminDTO newAdminDTO) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException(id));
        admin.emptyTournaments();
        for(long tournamentId : newAdminDTO.getTournamentIds()){
            admin.addTournament(tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new TournamentNotFoundException(tournamentId)));
        }
        return new AdminDTO(adminRepository.save(new Admin(newAdminDTO, admin.getPassword(), admin.getTournaments())));
    }
    public ParticipantDTO updateParticipant(long id, Participant newParticipant){
        return participantRepository.findById(id).map(participant -> {
            return new ParticipantDTO(participantRepository.save(new Participant(newParticipant)));
        }).orElseThrow( ()-> new ParticipantNotFoundException(id));
    }
    public ParticipantDTO updateParticipantDTO(long id, ParticipantDTO newParticipantDTO) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow( ()-> new ParticipantNotFoundException(id));
        participant.emptyEnrolments();
        participant.emptyTeams();
        for(long enrolmentId : newParticipantDTO.getEnrolments()){
            participant.addEnrolment(enrolmentRepository.findById(enrolmentId)
                    .orElseThrow(() -> new EnrolmentNotFoundException(enrolmentId)));
        }
        for(long teamId : newParticipantDTO.getTeams()){
            participant.addTeam(teamRepository.findById(teamId)
                    .orElseThrow(() -> new TeamNotFoundException(teamId)));
        }
        return new ParticipantDTO(participantRepository.save(new Participant(newParticipantDTO, participant.getPassword(), participant.getEnrolments(), participant.getTeams())));
    }

    public boolean addTournamentToAdmin(long adminId, Tournament tournament){
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException(adminId));
        if(admin.addTournament(tournament)){
            adminRepository.save(admin);
            return true;
        } else {
            return false;
        }
    }

}
