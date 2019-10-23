package YERgen2.demo.controller;

import YERgen2.demo.Exceptions.AdminNotFoundException;
import YERgen2.demo.Exceptions.ParticipantNotFoundException;
import YERgen2.demo.model.Admin;
import YERgen2.demo.model.Participant;
import YERgen2.demo.model.Tournament;
import YERgen2.demo.repositories.AdminRepository;
import YERgen2.demo.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    AccountService(AdminRepository adminRepository, ParticipantRepository participantRepository){
        this.adminRepository = adminRepository;
        this.participantRepository = participantRepository;
    }

    public Admin saveAdmin(Admin participant){
        return adminRepository.save(participant);
    }
    public Participant saveParticipant(Participant participant){
        return participantRepository.save(participant);
    }

    public Admin findAdminById(long id){
        return adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException(id));
    }
    public Participant findParticipantById(Long id){
        return participantRepository.findById(id)
                .orElseThrow(() -> new ParticipantNotFoundException(id));
    }

    public Iterable <Admin> findAllAdmin(){
        return adminRepository.findAll();
    }
    public Iterable <Participant> findAllParticipant(){
        return participantRepository.findAll();
    }

    public void deleteAdminById(long id) {
        adminRepository.deleteById(id);
    }
    public void deleteParticipantById(Long id) {
        participantRepository.deleteById(id);
    }

    public Admin updateAdmin(long id, Admin newAdmin) {
        return adminRepository.findById(id).map(admin -> {
            admin.setEmail(newAdmin.getEmail());
            admin.setPassword(newAdmin.getPassword());
            admin.setName(newAdmin.getName());
            admin.setTournaments(newAdmin.getTournaments());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new AdminNotFoundException(newAdmin.getId()));
    }
    public Participant updateParticipant(long id, Participant newParticipant){
        return participantRepository.findById(id).map(participant -> {
            participant.setEmail(newParticipant.getEmail());
            participant.setPassword(newParticipant.getPassword());
            participant.setFirstName(newParticipant.getFirstName());
            participant.setLastName(newParticipant.getLastName());
            participant.setMale(newParticipant.isMale());
            participant.setDateOfBirth(newParticipant.getDateOfBirth());
            participant.setPlayerLevel(newParticipant.getPlayerLevel());
            participant.setLeagueNumber(newParticipant.getLeagueNumber());
            participant.setEnrolment(newParticipant.getEnrolment());
            participant.setTeams(newParticipant.getTeams());
            return participantRepository.save(participant);
        }).orElseThrow( ()-> new ParticipantNotFoundException(id));
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
