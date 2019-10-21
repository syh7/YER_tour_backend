package YERgen2.demo.Exceptions;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(long id){
        super("Could not find admin " + id);
    }
}
