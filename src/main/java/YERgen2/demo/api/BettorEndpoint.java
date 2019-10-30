package YERgen2.demo.api;

import YERgen2.demo.DTO.BettorDTO;
import YERgen2.demo.controller.AccountService;
import YERgen2.demo.controller.BetService;
import YERgen2.demo.model.Bettor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BettorEndpoint {
    @Autowired
    private AccountService accountservice;
    @Autowired
    private BetService betService;

    @GetMapping("/bettors")
    public List<BettorDTO> getAllBettors(){
        return (List<BettorDTO>) accountservice.findAllBettor();
    }

    @GetMapping("/bettors/{id}")
    public BettorDTO getBettor(@PathVariable long id){
        return accountservice.findBettorById(id);
    }
}
