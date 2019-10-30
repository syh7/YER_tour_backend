package YERgen2.demo.api;

import YERgen2.demo.DTO.AdminDTO;
import YERgen2.demo.controller.AccountService;
import YERgen2.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminEndpoint {

    @Autowired
    private AccountService accountService;

    @PostMapping("/admins")
    public Admin newAdmin(@RequestBody Admin newAdmin) {
        return accountService.saveAdmin(newAdmin);
    }

    @GetMapping(value="/admins")
    public List<AdminDTO> getAllAdmins(){
        return (List<AdminDTO>) accountService.findAllAdmin();
    }

    @GetMapping(value = "admins/{id}", produces = "application/json")
    public AdminDTO getAdmin(@PathVariable long id) {
        return accountService.findAdminById(id);
    }

    @PutMapping("/admins/{id}/newpassword")
    public AdminDTO updateAdmin(@RequestBody Admin newAdmin, @PathVariable long id) {
        return accountService.updateAdmin(id, newAdmin);
    }
    @PutMapping("/admins/{id}")
    public AdminDTO updateAdminDTO(@RequestBody AdminDTO newAdminDTO, @PathVariable long id) {
        return accountService.updateAdminDTO(id, newAdminDTO);
    }

    @DeleteMapping("/admins/{id}")
    public void deleteAdmin(@PathVariable long id) {
        accountService.deleteAdminById(id);
    }

}
