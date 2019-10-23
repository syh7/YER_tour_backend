package YERgen2.demo.DTO;

import YERgen2.demo.model.Enrolment;
import YERgen2.demo.model.Participant;

import java.util.List;

public class EnrolRequestWrapper {
    
    public Participant participant;
    public List<Enrolment> enrolments;

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
