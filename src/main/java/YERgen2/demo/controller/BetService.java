package YERgen2.demo.controller;

import YERgen2.demo.DTO.BetDTO;
import YERgen2.demo.DTO.BettorDTO;
import YERgen2.demo.Exceptions.BettorNotFoundException;
import YERgen2.demo.Exceptions.GameNotFoundException;
import YERgen2.demo.Exceptions.TeamNotFoundException;
import YERgen2.demo.model.Bet;
import YERgen2.demo.model.Bettor;
import YERgen2.demo.model.Game;
import YERgen2.demo.model.Team;
import YERgen2.demo.repositories.BetRepository;
import YERgen2.demo.repositories.BettorRepository;
import YERgen2.demo.repositories.GameRepository;
import YERgen2.demo.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BetService {

    @Autowired
    private BettorRepository bettorRepository;
    @Autowired
    private BetRepository betRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TeamRepository teamRepository;

    public BetService(BettorRepository bettorRepository, BetRepository betRepository, GameRepository gameRepository,
                      TeamRepository teamRepository){
        this.bettorRepository = bettorRepository;
        this.betRepository = betRepository;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    public List<BetDTO> findBets(long bettorId){
        Bettor bettor = bettorRepository.findById(bettorId)
                .orElseThrow(() -> new BettorNotFoundException(bettorId));
        List<Bet> bets = bettor.getBets();
        List<BetDTO> betDTOs = new ArrayList<>();
        for(Bet bet : bets){
            betDTOs.add(new BetDTO(bet));
        }
        return betDTOs;
    }

    //Does not check for wallet value
    public BetDTO addBetToBettor(long bettorId, BetDTO betDTO){
        Bettor bettor = bettorRepository.findById(bettorId)
                .orElseThrow(() -> new BettorNotFoundException(bettorId));
        Game game = gameRepository.findById(betDTO.getGameId())
                .orElseThrow(() -> new GameNotFoundException(betDTO.getGameId()));
        Team team = teamRepository.findById(betDTO.getTeamId())
                .orElseThrow(() -> new TeamNotFoundException(betDTO.getTeamId()));
        Bet bet = new Bet(betDTO, bettor, game, team);
        bet = betRepository.save(bet);
        if(bettor.addBet(bet)){
            System.out.println("Added bet \"" + bet.toString() + "\" to " + bettor.getUserName());
            bettorRepository.save(bettor);
            return new BetDTO(betRepository.save(bet));
        } else {
            return null;
        }
    }

    public Bet saveBet(Bet bet){
        Bettor bettor = bettorRepository.findById(bet.getBettor().getId())
                .orElseThrow(() -> new BettorNotFoundException(bet.getBettor().getId()));
        if(bettor.addBet(bet)){
            bettorRepository.save(bettor);
            return betRepository.save(bet);
        } else {
            return null;
        }
    }

    public BettorDTO addToWallet(long bettorID, double amount){
        Bettor bettor = bettorRepository.findById(bettorID)
                .orElseThrow(() -> new BettorNotFoundException(bettorID));
        bettor.addToWallet(amount);
        return new BettorDTO(bettorRepository.save(bettor));
    }

}
