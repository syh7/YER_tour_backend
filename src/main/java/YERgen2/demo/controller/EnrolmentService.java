package YERgen2.demo.controller;

import YERgen2.demo.model.Enrolment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EnrolmentService {
    @Autowired
    private EnrolmentRepository enrolmentRepository;

    EnrolmentService(EnrolmentRepository enrolmentRepository){
        this.enrolmentRepository = enrolmentRepository;
    }

    public Enrolment save(Enrolment participant){
        return enrolmentRepository.save(participant);
    }

    public Optional<Enrolment> findById(Long id){
        return enrolmentRepository.findById(id);
    }

    public Iterable <Enrolment> findAll(){
        return enrolmentRepository.findAll();
    }

    public void deleteById(Long id) {
        enrolmentRepository.deleteById(id);
    }
}
