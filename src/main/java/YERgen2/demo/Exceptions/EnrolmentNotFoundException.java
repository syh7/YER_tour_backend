package YERgen2.demo.Exceptions;

public class EnrolmentNotFoundException extends RuntimeException {
    public EnrolmentNotFoundException(long id){
        super("Could not find enrolment: " + id);
    }
}
