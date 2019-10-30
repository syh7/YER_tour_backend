package YERgen2.demo.controller;

import YERgen2.demo.DTO.BettorDTO;
import YERgen2.demo.model.Bet;
import YERgen2.demo.model.Bettor;
import YERgen2.demo.repositories.BettorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetService {

    @Autowired
    private BettorRepository betterrepository;

    public BetService(BettorRepository betterrepository){
        this.betterrepository = betterrepository;
    }

    //methods

}
