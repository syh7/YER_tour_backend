package YERgen2.demo.Exceptions;

public class BettorNotFoundException extends RuntimeException {
    public BettorNotFoundException(long id){
        super("Could not find bettor " + id);
    }
}
