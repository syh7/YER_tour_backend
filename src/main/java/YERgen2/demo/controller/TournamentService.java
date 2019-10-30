package YERgen2.demo.controller;

import YERgen2.demo.DTO.*;
import YERgen2.demo.Exceptions.*;
import YERgen2.demo.model.*;
import YERgen2.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ResultRepository resultRepository;

    TournamentService(EnrolmentRepository enrolmentRepository, TournamentRepository tournamentRepository,
                      ParticipantRepository participantRepository, AdminRepository adminRepository,
                      TeamRepository teamRepository, GameRepository gameRepository,
                      ResultRepository resultRepository){
        this.enrolmentRepository = enrolmentRepository;
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
        this.adminRepository = adminRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
        this.resultRepository = resultRepository;
    }

    public Tournament saveTournament(NewTournamentWrapper newTournamentWrapper){
        Admin admin = adminRepository.findById(newTournamentWrapper.getAdminId())
                .orElseThrow(() -> new AdminNotFoundException(newTournamentWrapper.getAdminId()));
        if(admin.addTournament(newTournamentWrapper.getTournament())){
            adminRepository.save(admin);
            return tournamentRepository.save(newTournamentWrapper.getTournament());
        } else {
            return null;
        }
    }
    public Enrolment saveEnrolment(Enrolment enrolment){
        return enrolmentRepository.save(enrolment);
    }
    public List<GameDTO> saveGames(long tournamentId, List<GameDTO> gameDTOs) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));

        for(GameDTO gameDTO : gameDTOs){
            Team teamA = teamRepository.findById(gameDTO.getTeamAId())
                    .orElseThrow(() -> new TeamNotFoundException(gameDTO.getTeamAId()));
            Team teamB = teamRepository.findById(gameDTO.getTeamBId())
                    .orElseThrow(() -> new TeamNotFoundException(gameDTO.getTeamBId()));
            Game game = new Game(gameDTO.getStage(), gameDTO.getDiscipline(), tournament, teamA, teamB);
            tournament.addGame(game);
            teamA.addGame(game);
            teamB.addGame(game);
            gameRepository.save(game);
            teamRepository.save(teamA);
            teamRepository.save(teamB);
        }
        tournamentRepository.save(tournament);

        return gameDTOs;
    }
    public GameDTO saveGame(long tournamentId, GameDTO gameDTO){
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        Team teamA = teamRepository.findById(gameDTO.getTeamAId())
                .orElseThrow(() -> new TeamNotFoundException(gameDTO.getTeamAId()));
        Team teamB = teamRepository.findById(gameDTO.getTeamBId())
                .orElseThrow(() -> new TeamNotFoundException(gameDTO.getTeamBId()));
        Game game = new Game(gameDTO.getStage(), gameDTO.getDiscipline(), tournament, teamA, teamB);
        tournament.addGame(game);
        teamA.addGame(game);
        teamB.addGame(game);
        game = gameRepository.save(game);
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        tournamentRepository.save(tournament);
        return new GameDTO(game);
    }

    public TournamentDTO findTournamentById(long id){
        return new TournamentDTO(tournamentRepository.findById(id)
                .orElseThrow(() -> new TournamentNotFoundException(id)));
    }
    public Enrolment findEnrolmentById(long id){
        return enrolmentRepository.findById(id)
                .orElseThrow(() -> new EnrolmentNotFoundException(id));
    }

    public Tournament updateTournament(long id, Tournament newTournament){
        return tournamentRepository.findById(id).map(tournament -> {
            return tournamentRepository.save(new Tournament(newTournament));
        }).orElseThrow(() -> new TournamentNotFoundException(id));
    }
    public GameDTO updateGame(GameDTO gameDTO) {
        Tournament tournament = tournamentRepository.findById(gameDTO.getTournamentId())
                .orElseThrow(() -> new TournamentNotFoundException(gameDTO.getTournamentId()));
        Team teamA = teamRepository.findById(gameDTO.getTeamAId())
                .orElseThrow(() -> new TeamNotFoundException(gameDTO.getTeamAId()));
        Team teamB = teamRepository.findById(gameDTO.getTeamBId())
                .orElseThrow(() -> new TeamNotFoundException(gameDTO.getTeamBId()));
        return new GameDTO(gameRepository.save(new Game(gameDTO.getStage(), gameDTO.getDiscipline(), tournament, teamA, teamB)));
    }

    public boolean existsTournamentById(long id){
        return tournamentRepository.existsById(id);
    }

    public Iterable<TournamentDTO> findAllTournament(){
        List<TournamentDTO> tournamentDTOs = new ArrayList<>();
        for(Tournament tournament : tournamentRepository.findAll()){
            tournamentDTOs.add(new TournamentDTO(tournament));
        }
        return tournamentDTOs;
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

    public Iterable<TournamentDTO> findTournamentByNameContaining(String name){
        List<TournamentDTO> tournamentDTOs = new ArrayList<>();
        for(Tournament tournament : tournamentRepository.findByNameContaining(name)){
            tournamentDTOs.add(new TournamentDTO(tournament));
        }
        return tournamentDTOs;
    }

    public List<EnrolmentDTO> findEnrolmentByTournamentId(long tournamentId){
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        List<EnrolmentDTO> enrolmentDTOs = new ArrayList<>();
        tournament.getEnrolments().forEach(enrolment -> enrolmentDTOs.add(new EnrolmentDTO(enrolment)));
        return enrolmentDTOs;
    }
    public Iterable<GameDTO> findGamesByTournamentId(long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        List<GameDTO> gameDTOs = new ArrayList<>();
        tournament.getGames().forEach(game -> gameDTOs.add(new GameDTO(game)));
        return gameDTOs;
    }
    public Iterable<TeamDTO> findTeamsByTournamentId(long tournamentId){
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        List<TeamDTO> teamDTOs = new ArrayList<>();
        tournament.getTeams().forEach(team -> teamDTOs.add(new TeamDTO(team)));
        return teamDTOs;
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
        newEnrolmentWrapper.getEnrolmentDTOs().forEach(enrolmentDTO -> {
            if(enrolmentDTO.getTournamentId() != tournamentId){
                throw new InputMismatchException("Tournament IDs don't match!");
            }
        });
        Participant participant = participantRepository.findById(newEnrolmentWrapper.getParticipantId())
                .orElseThrow(() -> new ParticipantNotFoundException(newEnrolmentWrapper.getParticipantId()));
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        newEnrolmentWrapper.getEnrolmentDTOs().forEach(enrolmentDTO -> {
            List<Participant> participants = new ArrayList<>();
            for(int i = 0; i < enrolmentDTO.getParticipantIds().length; i++){
                participants.add(participantRepository.findById(enrolmentDTO.getParticipantIds()[i])
                .orElseThrow(() -> new ParticipantNotFoundException((long) 0)));
            }
            Enrolment enrolment = new Enrolment(enrolmentDTO, tournament, participants);
            enrolment = enrolmentRepository.save(enrolment);
            participant.addEnrolment(enrolment);
            participantRepository.save(participant);
            tournament.addEnrolment(enrolment);
            tournamentRepository.save(tournament);
        });
        return new ParticipantDTO(participant);
    }

    /**
     * Doesn't use NewEnrolmentWrapper, which we want it to
     * @param tournamentId tournament ID
     * @param newEnrolment new enrolment
     * @return updated enrolment
     */
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
    public List<EnrolmentDTO> updateEnrolments(NewEnrolmentWrapper newEnrolmentWrapper) {
        List<EnrolmentDTO> enrolmentDTOs = new ArrayList<>();
        newEnrolmentWrapper.getEnrolmentDTOs().forEach(enrolmentDTO -> {
            List<Participant> participants = new ArrayList<>();
            for(int i = 0; i < enrolmentDTO.getParticipantIds().length; i++){
                participants.add(participantRepository.findById(enrolmentDTO.getParticipantIds()[i])
                        .orElseThrow(() -> new ParticipantNotFoundException((long) 0)));
            }
            Enrolment enrolment = new Enrolment(enrolmentDTO, tournamentRepository.findById(enrolmentDTO.getTournamentId())
                    .orElseThrow(() -> new TournamentNotFoundException(enrolmentDTO.getTournamentId())), participants);
            enrolmentDTOs.add(enrolmentDTO);
        });
        return enrolmentDTOs;
    }

    public Iterable<EnrolmentDTO> findEnrolmentByTournamentIdAndDiscipline(long tournamentId, int discipline){
        List<EnrolmentDTO> enrolmentDTOs = new ArrayList<>();
        enrolmentRepository.findByTournamentIdAndDiscipline(tournamentId, Discipline.values()[discipline])
                .forEach(enrolment -> enrolmentDTOs.add(new EnrolmentDTO(enrolment)));
        return enrolmentDTOs;
    }

    /**
     * @param tournamentId tournamentId
     * @return List of made singles teams
     */
    public List<Team> makeSingleTeams(long tournamentId){
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));

        List<Team> teams = new ArrayList<>();
        List<Enrolment> menEnrolments = (List<Enrolment>) enrolmentRepository.findByTournamentIdAndDiscipline(tournamentId, Discipline.MENSINGLES);
        List<Enrolment> womenEnrolments = (List<Enrolment>) enrolmentRepository.findByTournamentIdAndDiscipline(tournamentId, Discipline.WOMENSINGLES);

        for(Enrolment enrolment : menEnrolments){
            Team team = new Team(enrolment);
            teams.add(team);
            teamRepository.save(team);
        }
        for(Enrolment enrolment : womenEnrolments){
            Team team = new Team(enrolment);
            teams.add(team);
            teamRepository.save(team);
        }

        for(Team team : teams){
            tournament.addTeam(team);
            for(Participant participant : team.getParticipants()){
                participant.addTeam(team);
                participantRepository.save(participant);
            }
        }
        tournamentRepository.save(tournament);

        return teams;
    }

    public Game finishGame(long tournamentId, long gameId, int[][] score){
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId));
        Result result = game.finishGame(score);
        resultRepository.save(result);
        game = gameRepository.save(game);
        tournamentRepository.save(tournament);
        return game;
    }

    public Tournament finishDiscipline(long tournamentId, Discipline discipline){
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
        for(Game game : tournament.getGames()){
            if(game.getStage() == Stage.FINAL && game.getDiscipline() == discipline){
                tournament.addResult(game.getResult());
            }
        }
        return tournamentRepository.save(tournament);
    }

}
