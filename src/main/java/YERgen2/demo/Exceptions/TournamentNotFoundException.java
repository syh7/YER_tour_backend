package YERgen2.demo.Exceptions;

public class TournamentNotFoundException extends RuntimeException {
    public TournamentNotFoundException(Long id) {
        super("Could not find tournament " + id);
    }
}
