package YERgen2.demo.DTO;

import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;

import java.util.List;

public class EnrolRequestWrapper {

    private long participantId;
    private List<Enrolment> enrolments;

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }
    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }

    public long getParticipantId(){
        return participantId;
    }
    public List<Enrolment> getEnrolments(){
        return enrolments;
    }

    public String toString(){
        String str = "";
        str += "participant: " + participantId + "\nEnrolments: [";
        for(Enrolment enrolment : enrolments){
            str += enrolment.getId() + " ";
        }
        str += "]";
        return str;
    }

}
