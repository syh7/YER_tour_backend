package YERgen2.demo.api;

import YERgen2.demo.controller.UserService;
import YERgen2.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping
public class UserEndpoint {

    @Autowired
    private UserService userService;

    @GetMapping(value = "user/{id}", produces = "application/json")
    public Optional<User> getUsers(@PathVariable long id) {
        return userService.findById(id);
    }

}
