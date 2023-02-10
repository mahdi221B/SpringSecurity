package tn.esprit.spring.services;

import tn.esprit.spring.entity.User;

import tn.esprit.spring.entity.Roles;
import tn.esprit.spring.entity.auth.AuthenticationRequest;
import tn.esprit.spring.entity.auth.AuthenticationResponse;
import tn.esprit.spring.entity.auth.RegisterRequest;

public interface UtilisateurService {
    public User addUser(User dto);
    public Roles addRole(Roles role);
    public void addRoleToUser(String roleName, String userName);
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
