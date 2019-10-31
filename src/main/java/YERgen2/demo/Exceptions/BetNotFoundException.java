package YERgen2.demo.Exceptions;

public class BetNotFoundException extends RuntimeException {
    public BetNotFoundException(Long id) {
        super("Could not find bet " + id);
    }
}
