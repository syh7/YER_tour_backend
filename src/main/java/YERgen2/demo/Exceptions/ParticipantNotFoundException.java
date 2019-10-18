package YERgen2.demo.Exceptions;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException(Long id) {
        super("Could not find participant " + id);
    }
}
