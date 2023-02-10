package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Roles;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.UtilisateurServiceImp;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UtilisateurController {
    private final UtilisateurServiceImp utilisateurServiceImp;
    @PostMapping("/addUser")
    @ResponseBody
    public void addUser (@RequestBody User dto){
        utilisateurServiceImp.addUser(dto);
    }
    @PostMapping("/addRole")
    @ResponseBody
    public void addRole (@RequestBody Roles role){
        utilisateurServiceImp.addRole(role);
    }
    @PutMapping("/addRoleToUser/{roleName}/{userName}")
    @ResponseBody
    public void addUser (@PathVariable("roleName") String roleName,@PathVariable("userName") String userName){
        utilisateurServiceImp.addRoleToUser(roleName,userName);
    }
}
