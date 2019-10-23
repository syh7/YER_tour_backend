package YERgen2.demo.DTO;

import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;

import java.util.List;

public class EnrolRequestWrapper {

    private Participant participant;
    private List<Enrolment> enrolments;

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    public Participant getParticipant(){
        return participant;
    }
    public List<Enrolment> getEnrolments(){
        return enrolments;
    }

    public String toString(){
        String str = "";
        str += "participant: " + participant.getId() + "\nEnrolments: [";
        for(Enrolment enrolment : enrolments){
            str += enrolment.getId() + " ";
        }
        str += "]";
        return str;
    }

}
