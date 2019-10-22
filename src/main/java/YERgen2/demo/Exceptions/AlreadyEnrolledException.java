package YERgen2.demo.Exceptions;

public class AlreadyEnrolledException extends RuntimeException {
    public AlreadyEnrolledException(long pid, long eid){
        super("Participant " + pid + " already enrolled in enrolment " + eid);
    }
}
