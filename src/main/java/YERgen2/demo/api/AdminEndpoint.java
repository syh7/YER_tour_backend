package YERgen2.demo.api;

import YERgen2.demo.Exceptions.AdminNotFoundException;
import YERgen2.demo.controller.AdminService;
import YERgen2.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AdminEndpoint {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admins")
    Admin newParticipant(@RequestBody Admin newAdmin) {
        return adminService.save(newAdmin);
    }

    @GetMapping(value="/admins")
    public List<Admin> getAllParticipants(){
        return (List<Admin>) adminService.findAll();
    }

    @GetMapping(value = "admins/{id}", produces = "application/json")
    public Admin getParticipant(@PathVariable long id) {
        return adminService.findById(id)
                .orElseThrow(() -> new AdminNotFoundException(id));
    }

    @PutMapping("/admins/{id}")
    Admin replaceAdmin(@RequestBody Admin newAdmin, @PathVariable Long id) {
        return adminService.findById(id)
                .map(participant -> {
                    participant.setEmail(newAdmin.getEmail());
                    participant.setPassword(newAdmin.getPassword());
                    return adminService.save(participant);
                })
                .orElseGet(() -> {
                    newAdmin.setId(id);
                    return adminService.save(newAdmin);
                });
    }

    @DeleteMapping("/admins/{id}")
    void deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
    }

}
