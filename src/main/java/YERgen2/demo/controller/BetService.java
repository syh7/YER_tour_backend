package YERgen2.demo.controller;

import YERgen2.demo.repositories.BettorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    @Autowired
    private BettorRepository betterrepository;

    public BetService(BettorRepository betterrepository){
        this.betterrepository = betterrepository;
    }

    //methods
}
