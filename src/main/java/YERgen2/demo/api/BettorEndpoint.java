package YERgen2.demo.api;

import YERgen2.demo.DTO.BetDTO;
import YERgen2.demo.DTO.BettorDTO;
import YERgen2.demo.controller.AccountService;
import YERgen2.demo.controller.BetService;
import YERgen2.demo.model.Bet;
import YERgen2.demo.model.Bettor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BettorEndpoint {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BetService betService;

    @GetMapping("/bettors")
    public List<BettorDTO> getAllBettors(){
        return (List<BettorDTO>) accountService.findAllBettor();
    }

    @GetMapping("/bettors/{id}")
    public BettorDTO getBettor(@PathVariable long id){
        return accountService.findBettorById(id);
    }

    @PostMapping("/bettors")
    public Bettor newBettor(@RequestBody Bettor newBettor){
        return accountService.saveBettor(newBettor);
    }

    @PutMapping("/bettors/{id}")
    public BettorDTO updateBettor(@RequestBody Bettor newBettor, @PathVariable long id) {
        return accountService.updateBettor(id, newBettor);
    }

    @PutMapping("/bettors/{id}/addWallet/{amount}")
    public BettorDTO updateBettorWallet(@PathVariable double amount, @PathVariable long id){
        return betService.addToWallet(id, amount);
    }

    @GetMapping("/bettors/{id}/bets")
    public List<BetDTO> getBets(@PathVariable long id){
        return betService.findBets(id);
    }

    @PostMapping("/bettors/{id}/placeBet")
    public BetDTO placeBet(@PathVariable long id, @RequestBody BetDTO betDTO){
        return betService.addBetToBettor(id, betDTO);
    }

    @DeleteMapping("bettors/{id}")
    public void deleteBettor(@PathVariable long id) {
        accountService.deleteBettorById(id);
    }
}
