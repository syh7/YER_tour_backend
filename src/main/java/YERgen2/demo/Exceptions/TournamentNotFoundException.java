package YERgen2.demo.Exceptions;

public class TournamentNotFoundException extends RuntimeException {
    TournamentNotFoundException(Long id) {
        super("Could not find tournament " + id);
    }
}
