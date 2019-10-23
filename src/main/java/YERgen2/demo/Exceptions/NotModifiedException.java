package YERgen2.demo.Exceptions;

public class NotModifiedException extends RuntimeException {
    public NotModifiedException(long id){
        super(id + " not modified.");
    }
}
