package YERgen2.demo.repositories;

import YERgen2.demo.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AdminRepository extends CrudRepository<Admin, Long> {
}
