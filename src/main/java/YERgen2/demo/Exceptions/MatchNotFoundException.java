package YERgen2.demo.Exceptions;

public class MatchNotFoundException extends RuntimeException {
    MatchNotFoundException(Long id) {
        super("Could not find participant " + id);
    }
}
