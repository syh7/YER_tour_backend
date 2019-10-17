package YERgen2.demo.controller;

import YERgen2.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRespository extends CrudRepository<User, Long> {
}
