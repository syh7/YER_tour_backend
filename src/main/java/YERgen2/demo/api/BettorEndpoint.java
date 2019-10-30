package YERgen2.demo.api;

import YERgen2.demo.DTO.BettorDTO;
import YERgen2.demo.controller.AccountService;
import YERgen2.demo.controller.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
