package YERgen2.demo.DTO;

import java.util.List;

public class EnrolRequestWrapper {

    private long participantId;
    private List<EnrolmentDTO> enrolmentDTOs;

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }
    public void setEnrolments(List<EnrolmentDTO> enrolmentDTOs) {
        this.enrolmentDTOs = enrolmentDTOs;
    }

    public long getParticipantId(){
        return participantId;
    }
    public List<EnrolmentDTO> getEnrolmentDTOs(){
        return enrolmentDTOs;
    }

    public String toString(){
        String str = "";
        str += "participant: " + participantId + "\nEnrolments: [";
        for(EnrolmentDTO enrolmentDTO : enrolmentDTOs){
            str += enrolmentDTO.getId() + " ";
        }
        str += "]";
        return str;
    }

}
