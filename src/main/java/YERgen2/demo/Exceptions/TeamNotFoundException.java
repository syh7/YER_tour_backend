package YERgen2.demo.Exceptions;

public class TeamNotFoundException extends RuntimeException {
    TeamNotFoundException(Long id) {
        super("Could not find participant " + id);
    }
}
