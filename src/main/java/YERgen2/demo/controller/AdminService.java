package YERgen2.demo.controller;

import YERgen2.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public Admin save(Admin participant){
        return adminRepository.save(participant);
    }

    public Optional<Admin> findById(Long id){
        return adminRepository.findById(id);
    }

    public Iterable <Admin> findAll(){
        return adminRepository.findAll();
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
}
