package YERgen2.demo.controller;

import YERgen2.demo.model.Enrolment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface EnrolmentRepository extends CrudRepository<Enrolment, Long> {
}
