package YERgen2.demo.controller;

import YERgen2.demo.DTO.BettorDTO;
import YERgen2.demo.Exceptions.BettorNotFoundException;
import YERgen2.demo.model.Bet;
import YERgen2.demo.model.Bettor;
import YERgen2.demo.repositories.BetRepository;
import YERgen2.demo.repositories.BettorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    @Autowired
    private BettorRepository bettorRepository;
    @Autowired
    private BetRepository betRepository;

    public BetService(BettorRepository bettorRepository, BetRepository betRepository){
        this.bettorRepository = bettorRepository;
        this.betRepository = betRepository;
    }

    public boolean addBetToBettor(long bettorId, Bet bet){
        Bettor bettor = bettorRepository.findById(bettorId)
                .orElseThrow(() -> new BettorNotFoundException(bettorId));
        if(bettor.addBet(bet)){
            betRepository.save(bet);
            bettorRepository.save(bettor);
            return true;
        } else {
            return false;
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
