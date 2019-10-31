package YERgen2.demo.controller;

import YERgen2.demo.DTO.AdminDTO;
import YERgen2.demo.DTO.BettorDTO;
import YERgen2.demo.DTO.ParticipantDTO;
import YERgen2.demo.DTO.TournamentDTO;
import YERgen2.demo.Exceptions.*;
import YERgen2.demo.model.*;
import YERgen2.demo.repositories.*;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private BettorRepository bettorRepository;
    @Autowired
    private BetRepository betRepository;

    AccountService(AdminRepository adminRepository, ParticipantRepository participantRepository,
                   EnrolmentRepository enrolmentRepository, TeamRepository teamRepository,
                   TournamentRepository tournamentRepository, BettorRepository bettorRepository,
                   BetRepository betRepository){
        this.adminRepository = adminRepository;
        this.participantRepository = participantRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.bettorRepository = bettorRepository;
        this.betRepository = betRepository;
    }

    public Admin saveAdmin(Admin participant){
        return adminRepository.save(participant);
    }
    public Participant saveParticipant(Participant participant){
        return participantRepository.save(participant);
    }
    public Bettor saveBettor(Bettor bettor){
        return bettorRepository.save(bettor);
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

    //=Betting=
    public BettorDTO findBettorById(Long id){
        Bettor bettor = bettorRepository.findById(id)
                .orElseThrow(() -> new BettorNotFoundException(id));
        return new BettorDTO(bettor);
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

    //=Betting=
    public Iterable <BettorDTO> findAllBettor(){
        List<BettorDTO> bettorDTOs = new ArrayList<>();
        bettorRepository.findAll().forEach(bettor -> {
            bettorDTOs.add(new BettorDTO(bettor));
        });
        return bettorDTOs;
    }

    public void deleteAdminById(long id) {
        adminRepository.deleteById(id);
    }
    public void deleteParticipantById(Long id) {
        participantRepository.deleteById(id);
    }
    public void deleteBettorById(Long id) {
        bettorRepository.deleteById(id);
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

    public BettorDTO updateBettor(long id, Bettor newBettor) {
        return bettorRepository.findById(id).map(bettor -> {
            return new BettorDTO(bettorRepository.save(new Bettor(bettor)));
        }).orElseThrow(() -> new BettorNotFoundException(newBettor.getId()));
    }
    public BettorDTO updateBettorDTO(long id, BettorDTO newBettorDTO) {
        Bettor bettor = bettorRepository.findById(id)
                .orElseThrow(() -> new BettorNotFoundException(id));
        bettor.clearBets();
        for(long betId : newBettorDTO.getBetIds()){
            bettor.addBet(betRepository.findById(betId)
                    .orElseThrow(() -> new BetNotFoundException(betId)));
        }
        return new BettorDTO(bettorRepository.save(new Bettor(newBettorDTO, bettor.getPassword(), bettor.getBets())));
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

    public Set<TournamentDTO> getParticipantTournaments(long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ParticipantNotFoundException(participantId));
        Set<TournamentDTO> tournamentDTOs = new HashSet<>();
        for(Enrolment enrolment : participant.getEnrolments()){
            tournamentDTOs.add(new TournamentDTO(enrolment.getTournament()));
        }
        return tournamentDTOs;
    }

    public int[] getAllResults(long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ParticipantNotFoundException(participantId));
        int total = 0, won = 0, lost = 0;
        for(Team team : participant.getTeams()){
            for(Game game : team.getGames()){
                total++;
                if(team == game.getWinningTeam()){
                    won++;
                } else {
                    lost++;
                }
            }
        }
        return new int[]{total, won, lost};
    }
}
