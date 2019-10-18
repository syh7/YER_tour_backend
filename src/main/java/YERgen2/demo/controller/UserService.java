package YERgen2.demo.controller;

import YERgen2.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRespository userRespository;

    public User save(User user){
        return userRespository.save(user);
    }

    public Optional<User> findById(Long id){
        return userRespository.findById(id);
    }

    public Iterable <User> findAll(){
        return userRespository.findAll();
    }
}
