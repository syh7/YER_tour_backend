package YERgen2.demo.repositories;

import YERgen2.demo.model.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

    //boolean existsEnrolmentById(long ParticipantId, long EnrolmentId);

}
