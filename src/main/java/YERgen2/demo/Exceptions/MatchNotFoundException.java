package YERgen2.demo.Exceptions;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(Long id) {
        super("Could not find participant " + id);
    }
}
