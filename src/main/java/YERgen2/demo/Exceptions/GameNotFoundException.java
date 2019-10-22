package YERgen2.demo.Exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("Could not find participant " + id);
    }
}
