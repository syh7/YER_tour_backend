package YERgen2.demo.Exceptions;

public class ParticipantNotFoundException extends RuntimeException {
    ParticipantNotFoundException(Long id) {
        super("Could not find participant " + id);
    }
}
