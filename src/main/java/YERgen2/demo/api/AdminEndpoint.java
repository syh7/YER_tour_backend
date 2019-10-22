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
    public Admin newAdmin(@RequestBody Admin newAdmin) {
        return adminService.save(newAdmin);
    }

    @GetMapping(value="/admins")
    public List<Admin> getAllAdmins(){
        return (List<Admin>) adminService.findAll();
    }

    @GetMapping(value = "admins/{id}", produces = "application/json")
    public Admin getAdmin(@PathVariable long id) {
        return adminService.findById(id);
    }

    @PutMapping("/admins/{id}")
    public Admin updateAdmin(@RequestBody Admin newAdmin, @PathVariable long id) {
        return adminService.updateAdmin(id, newAdmin);
    }

    @DeleteMapping("/admins/{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminService.deleteById(id);
    }

}
